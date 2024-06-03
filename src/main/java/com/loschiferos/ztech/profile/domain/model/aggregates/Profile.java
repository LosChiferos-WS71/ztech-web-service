package com.loschiferos.ztech.profile.domain.model.aggregates;

import com.loschiferos.ztech.profile.domain.model.valueobjects.EmailAddress;
import com.loschiferos.ztech.profile.domain.model.valueobjects.PersonName;
import com.loschiferos.ztech.profile.domain.model.valueobjects.StreetAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    private String password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "address_number")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "address_zipCode"))
    })
    private StreetAddress address;

    private int phone;

    private String photo;

    public Profile(String firstName, String lastName, String email, String password, String street, String number, String city, String zipCode, int phone, String photo) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.password = password;
        this.address = new StreetAddress(street, number, city, zipCode);
        this.phone = phone;
        this.photo = photo;
    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public String getFullName() {
        return this.name.getFullName();
    }

    public String getStreetAddress() {
        return this.address.getStreetAddress();
    }

    public String getEmailAddress() {
        return this.email.address();
    }
}
