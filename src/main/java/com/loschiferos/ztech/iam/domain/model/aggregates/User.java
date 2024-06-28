package com.loschiferos.ztech.iam.domain.model.aggregates;


import com.loschiferos.ztech.iam.domain.model.entities.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    // Otros campos, getters y setters

    public User() {
        roles = new HashSet<>();
    }

    public User(String email) {
        this();
        this.email = email;
    }

    public User(String email, List<Role> roles) {
        this(email);
        addRoles(roles);
    }

    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }


}
