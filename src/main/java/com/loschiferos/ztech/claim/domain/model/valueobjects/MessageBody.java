package com.loschiferos.ztech.claim.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class MessageBody {
    private String subject;
    private String description;

    public MessageBody(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }
}
