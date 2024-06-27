package com.loschiferos.ztech.iam.interfaces.rest.transform;

import com.loschiferos.ztech.iam.domain.model.entities.Role;
import com.loschiferos.ztech.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
