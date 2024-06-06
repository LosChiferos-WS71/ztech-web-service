package com.loschiferos.ztech.loan.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FlowerpotId(Long flowerpotId) {
    public FlowerpotId() { this(0L); }

    public FlowerpotId {
        if (flowerpotId < 0) {
            throw new IllegalArgumentException("Flowerpot id cannot be negative");
        }
    }
}
