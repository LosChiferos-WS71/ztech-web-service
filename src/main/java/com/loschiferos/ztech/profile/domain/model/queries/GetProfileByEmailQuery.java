package com.loschiferos.ztech.profile.domain.model.queries;

import com.loschiferos.ztech.profile.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
