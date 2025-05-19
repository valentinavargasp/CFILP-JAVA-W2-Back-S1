package com.alkemy.wallet.models.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_person")
    private int idPerson;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private String location;

    @Column(name = "province")
    private String province;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "identity_card")
    private int identityCard;

    @Column(name = "date_birth")
    private String dateBirth; // AAAA-MM-DD

    public String getFullName() {
        return name + " " + lastName;
    }
}
