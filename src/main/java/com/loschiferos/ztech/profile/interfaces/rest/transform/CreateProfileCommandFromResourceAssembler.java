package com.loschiferos.ztech.profile.interfaces.rest.transform;

import com.loschiferos.ztech.profile.domain.model.commands.CreateProfileCommand;
import com.loschiferos.ztech.profile.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(), resource.lastName(), resource.email(), resource.password(), resource.street(), resource.number(), resource.city(), resource.zipCode(), resource.phone(), resource.photo());
    }
}
