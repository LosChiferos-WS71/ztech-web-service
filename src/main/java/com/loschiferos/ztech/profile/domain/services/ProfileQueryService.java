package com.loschiferos.ztech.profile.domain.services;

import com.loschiferos.ztech.profile.domain.model.aggregates.Profile;
import com.loschiferos.ztech.profile.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByEmailQuery query);
    Optional<Profile> handle(GetProfileByIdQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
}
