package com.loschiferos.ztech.profile.domain.services;

import com.loschiferos.ztech.profile.domain.model.commands.CreateProfileCommand;

public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);
}
