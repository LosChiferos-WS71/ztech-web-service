package com.loschiferos.ztech.iam.application.internal.queryservices;

import com.loschiferos.ztech.iam.domain.model.entities.Role;
import com.loschiferos.ztech.iam.domain.model.queries.GetAllRolesQuery;
import com.loschiferos.ztech.iam.domain.model.queries.GetRoleByNameQuery;
import com.loschiferos.ztech.iam.domain.services.RoleQueryService;
import com.loschiferos.ztech.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link RoleQueryService} interface
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}
