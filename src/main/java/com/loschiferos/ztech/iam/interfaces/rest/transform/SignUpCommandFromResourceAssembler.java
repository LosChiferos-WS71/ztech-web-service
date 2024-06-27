package com.loschiferos.ztech.iam.interfaces.rest.transform;

import com.loschiferos.ztech.iam.domain.model.commands.SignUpCommand;
import com.loschiferos.ztech.iam.domain.model.entities.Role;
import com.loschiferos.ztech.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource signUpResource) {
        var roles = signUpResource.roles() != null ? signUpResource.roles().stream()
                .map(name -> Role.toRoleFromName(name))
                .toList() : new ArrayList<Role>();
        return new SignUpCommand(signUpResource.username(), signUpResource.password(), roles);
    }
}
