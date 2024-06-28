package com.loschiferos.ztech.iam.application.internal.commandservices;

import com.loschiferos.ztech.iam.application.internal.outboundservices.acl.ExternalPlantOwnerService;
import com.loschiferos.ztech.iam.application.internal.outboundservices.hashing.HashingService;
import com.loschiferos.ztech.iam.application.internal.outboundservices.tokens.TokenService;
import com.loschiferos.ztech.iam.domain.model.aggregates.User;
import com.loschiferos.ztech.iam.domain.model.commands.SignInCommand;
import com.loschiferos.ztech.iam.domain.model.commands.SignUpCommand;
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

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository, ExternalPlantOwnerService externalPlantOwnerService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.externalPlantOwnerService = externalPlantOwnerService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) throw new RuntimeException("User already exists");
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        var user = new User(command.email(), roles);
        userRepository.save(user);
        var plantOwnerId = externalPlantOwnerService.savePlantOwner(command.name(), command.email(), command.address(), command.phone(), command.photo(), command.dni(), command.birthday(), command.gender());
        if(plantOwnerId.isEmpty()) {
            throw new ValidationException("Plant Owner not saved");
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
