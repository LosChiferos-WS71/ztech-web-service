package com.loschiferos.ztech.claim.domain.model.aggregates;

import com.loschiferos.ztech.claim.domain.model.valueobjects.MessageBody;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlowerpotClaimRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MessageBody messageBody;

    public FlowerpotClaimRequest(MessageBody messageBody) {
        this.messageBody = messageBody;
    }
}
