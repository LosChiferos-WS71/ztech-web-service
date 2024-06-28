package com.loschiferos.ztech.iam.infrastructure.tokens.jwt;

import com.loschiferos.ztech.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;


public interface BearerTokenService extends TokenService {
    String generateToken(Authentication authentication);

    String getBearerTokenFrom(HttpServletRequest request);

}
