package com.loschiferos.ztech.flowerpot.domain.model.aggregates;

import com.loschiferos.ztech.flowerpot.domain.model.entities.Plant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserFlowerpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @OneToOne
    @JoinColumn(name = "flowerpot_id")
    private Plant flowerpotId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Plant userId;

    @Getter
    @OneToOne
    @JoinColumn(name = "plant_id")
    private Plant plantId;

    private String name;

    private String photo;
}
