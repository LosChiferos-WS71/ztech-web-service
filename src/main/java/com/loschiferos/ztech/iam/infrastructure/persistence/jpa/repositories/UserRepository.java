package com.loschiferos.ztech.iam.infrastructure.persistence.jpa.repositories;

import com.loschiferos.ztech.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface represents the repository for the {@link User} aggregate.
 * <p>
 *     This repository is responsible for handling all the persistence operations related to the {@link User} aggregate.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * This method is used to find a user by its username.
     *
     * @param email the user username.
     * @return the {@link User} object.
     */
    Optional<User> findByEmail(String email);

    /**
     * This method is used to check if a user exists by its username.
     *
     * @param email the user username.
     * @return true if the user exists, false otherwise.
     */
    boolean existsByEmail(String email);
}
