package com.loschiferos.ztech.iam.application.internal.commandservices;

import com.loschiferos.ztech.iam.application.internal.outboundservices.acl.ExternalPlantOwnerService;
import com.loschiferos.ztech.iam.application.internal.outboundservices.acl.ExternalSupplierService;
import com.loschiferos.ztech.iam.application.internal.outboundservices.hashing.HashingService;
import com.loschiferos.ztech.iam.application.internal.outboundservices.tokens.TokenService;
import com.loschiferos.ztech.iam.domain.model.aggregates.User;
import com.loschiferos.ztech.iam.domain.model.commands.SignInCommand;
import com.loschiferos.ztech.iam.domain.model.commands.SignUpCommand;
import com.loschiferos.ztech.iam.domain.model.entities.Role;
import com.loschiferos.ztech.iam.domain.model.valueobjects.PlantOwnerId;
import com.loschiferos.ztech.iam.domain.model.valueobjects.Roles;
import com.loschiferos.ztech.iam.domain.model.valueobjects.SupplierId;
import com.loschiferos.ztech.iam.domain.services.UserCommandService;
import com.loschiferos.ztech.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.loschiferos.ztech.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.loschiferos.ztech.shared.domain.exceptions.ValidationException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final ExternalPlantOwnerService externalPlantOwnerService;
    private final ExternalSupplierService externalSupplierService;
    private Optional<PlantOwnerId> plantOwnerId;
    private Optional<SupplierId> supplierId;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository, ExternalPlantOwnerService externalPlantOwnerService, ExternalSupplierService externalSupplierService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.externalPlantOwnerService = externalPlantOwnerService;
        this.externalSupplierService = externalSupplierService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) throw new RuntimeException("User already exists");

        var roles = command.roles().stream().map(role -> {
            var foundRole = roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role not found"));
            System.out.println("Role found: " + foundRole.getName());
            return foundRole;
        }).toList();

        var user = new User(command.email(), roles);
        userRepository.save(user);

        boolean isPlantOwner = roles.stream().anyMatch(role -> role.getName().equals(Roles.ROLE_PLANT_OWNER));

        boolean isSupplier = roles.stream().anyMatch(role -> role.getName().equals(Roles.ROLE_SUPPLIER));

        if(isPlantOwner) {
            plantOwnerId = externalPlantOwnerService.savePlantOwner(command.name(), command.email(), command.address(), command.phone(), command.photo(), command.dni(), command.birthday(), command.gender());
            if(plantOwnerId.isEmpty()) {
                throw new ValidationException("Plant Owner Id cannot be empty");
            }
        }

        if(isSupplier) {
            supplierId = externalSupplierService.saveSupplier(command.name(), command.email(), command.address(), command.phone(), command.photo(), command.ruc());
            if(supplierId.isEmpty()) {
                throw new ValidationException("Supplier Id cannot be empty");
            }
        }

        return userRepository.findByEmail(command.email());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(new ImmutablePair<>(user.get(), token));
    }
}
