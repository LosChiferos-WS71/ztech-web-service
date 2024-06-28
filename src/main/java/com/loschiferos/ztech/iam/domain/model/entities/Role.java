package com.loschiferos.ztech.iam.domain.model.entities;


import com.loschiferos.ztech.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Role
 * Entity with default Roles
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
@Entity
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    /**
     * Role Constructor
     *
     * @param name Role name
     */
    public Role(Roles name) {
        this.name = name;
    }

    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_PLANT_OWNER);
    }

    public String getStringName() {
        return name.name();
    }

    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }
    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }
        return roles;
    }
}
