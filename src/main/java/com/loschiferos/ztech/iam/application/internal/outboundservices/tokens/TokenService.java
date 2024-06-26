package com.loschiferos.ztech.iam.application.internal.outboundservices.tokens;

/**
 * Token service interface.
 * <p>
 *     This interface is used to generate and validate a token.
 * </p>
 */
public interface TokenService {
    /**
     * Generates a token.
     * @param username the username.
     * @return the generated token.
     */
    String generateToken(String username);

    /**
     * Gets the username from a token.
     * @param token the token.
     * @return the username.
     */
    String getUsernameFromToken(String token);

    /**
     * Validates a token.
     * @param token the token.
     * @return true if the token is valid, false otherwise.
     */
    boolean validateToken(String token);
}

