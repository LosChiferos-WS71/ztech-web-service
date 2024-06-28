package com.loschiferos.ztech.iam.infrastructure.hashing.bcrypt;

import com.loschiferos.ztech.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
