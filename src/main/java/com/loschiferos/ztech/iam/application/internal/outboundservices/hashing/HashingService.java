package com.loschiferos.ztech.iam.application.internal.outboundservices.hashing;

/**
 * Hashing service interface.
 * <p>
 *     This interface is used to hash a password.
 * </p>
 */
public interface HashingService {
    /**
     * Hashes a password.
     * @param rawPassword the password to be hashed.
     * @return the hashed password.
     */
    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
