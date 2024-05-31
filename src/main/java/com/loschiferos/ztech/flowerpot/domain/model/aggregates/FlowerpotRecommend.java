package com.loschiferos.ztech.flowerpot.domain.model.aggregates;

import com.loschiferos.ztech.flowerpot.domain.model.entities.Flowerpot;
import com.loschiferos.ztech.flowerpot.domain.model.entities.Plant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FlowerpotRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "flowerpot_id")
    private Flowerpot flowerpotId;

    private String text;

    public FlowerpotRecommend(Flowerpot flowerpotId, String text) {
        this();
        this.flowerpotId = flowerpotId;
        this.text = text;
    }
}
