package com.loschiferos.ztech.iam.application.internal.commandservices;

import com.loschiferos.ztech.iam.domain.model.commands.SeedRolesCommand;
import com.loschiferos.ztech.iam.domain.model.entities.Role;
import com.loschiferos.ztech.iam.domain.model.valueobjects.Roles;
import com.loschiferos.ztech.iam.domain.services.RoleCommandService;
import com.loschiferos.ztech.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Implementation of {@link RoleCommandService}
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role))
                roleRepository.save(new Role(Roles.valueOf(role.name())));
        });
    }
}
