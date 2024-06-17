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
public class PlantOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private Profile profile;

    private Long dni;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String gender;

    public PlantOwner(Profile profile, Long dni, Date birthday, String gender) {
        this.profile = profile;
        this.dni = dni;
        this.birthday = birthday;
        this.gender = gender;
    }

    public PlantOwner updatePlantOwner(String name, String email, String address, Long phone, String photo, Long dni, Date birthday, String gender) {
        this.profile = new Profile(name, email, address, phone, photo);
        this.dni = dni;
        this.birthday = birthday;
        this.gender = gender;
        return this;
    }
}
