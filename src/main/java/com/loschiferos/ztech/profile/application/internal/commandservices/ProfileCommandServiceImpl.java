package com.loschiferos.ztech.profile.application.internal.commandservices;

import com.loschiferos.ztech.profile.domain.model.aggregates.Profile;
import com.loschiferos.ztech.profile.domain.model.commands.CreateProfileCommand;
import com.loschiferos.ztech.profile.domain.model.valueobjects.EmailAddress;
import com.loschiferos.ztech.profile.domain.services.ProfileCommandService;
import com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Long handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
        });
        var profile = new Profile(command.firstName(), command.lastName(), command.email(), command.password(),
                command.street(), command.number(), command.city(), command.zipCode(), command.phone(), command.photo());
        profileRepository.save(profile);
        return profile.getId();
    }
}
