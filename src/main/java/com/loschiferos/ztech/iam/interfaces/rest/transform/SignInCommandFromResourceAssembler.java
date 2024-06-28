package com.loschiferos.ztech.iam.interfaces.rest.transform;

import com.loschiferos.ztech.iam.domain.model.commands.SignInCommand;
import com.loschiferos.ztech.iam.interfaces.rest.resources.SignInResource;

public class    SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email());
    }
}
