package com.alkemy.wallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_person")
    private int idPerson;

    @Column(name="id_person")
    private int id;


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

    // Constructores
    public Person() {
    }

    public Person(int id, String name, String lastName, String address, String location, String province,
            String phoneNumber, int identityCard, String dateBirth) {
        this.idPerson = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.location = location;
        this.province = province;
        this.phoneNumber = phoneNumber;
        this.identityCard = identityCard;
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + idPerson +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", province='" + province + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identityCard=" + identityCard +
                ", dateBirth='" + dateBirth + '\'' +
                '}';
    }
}
