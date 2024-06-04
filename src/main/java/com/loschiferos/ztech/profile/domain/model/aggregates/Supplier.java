package com.loschiferos.ztech.profile.domain.model.aggregates;

import com.loschiferos.ztech.profile.domain.model.valueobjects.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private Profile profile;

    private String ruc;

    public Supplier(Profile profile, String ruc) {
        this.profile = profile;
        this.ruc = ruc;
    }
}
