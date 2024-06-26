package com.loschiferos.ztech.iam.domain.services;

import com.loschiferos.ztech.iam.domain.model.entities.Role;
import com.loschiferos.ztech.iam.domain.model.queries.GetAllRolesQuery;
import com.loschiferos.ztech.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the query service for the {@link Role} entity.
 *
 */
public interface RoleQueryService {
    /**
     * This method handles the {@link GetAllRolesQuery} query.
     *
     * @param query the {@link GetAllRolesQuery} query.
     * @return the list of {@link Role} entities.
     */
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
