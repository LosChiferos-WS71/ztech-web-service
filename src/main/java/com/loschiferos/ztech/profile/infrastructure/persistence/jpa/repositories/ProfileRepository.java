package com.loschiferos.ztech.profile.infrastructure.persistence.jpa.repositories;

import com.loschiferos.ztech.profile.domain.model.aggregates.Profile;
import com.loschiferos.ztech.profile.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmail(EmailAddress emailAddress);
}
