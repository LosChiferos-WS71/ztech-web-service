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
public class UserFlowerpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @OneToOne
    @JoinColumn(name = "flowerpot_id")
    private Flowerpot flowerpotId;

    //@Getter
    //@ManyToOne
    //@JoinColumn(name = "user_id")
    private String userId;

    @Getter
    @OneToOne
    @JoinColumn(name = "plant_id")
    private Plant plantId;

    private String name;

    private String photo;

    public  UserFlowerpot(Flowerpot flowerpotId, String userId, Plant plantId, String name, String photo) {
        this();
        this.flowerpotId = flowerpotId;
        this.userId = userId;
        this.plantId = plantId;
        this.name = name;
        this.photo = photo;
    }
}
