package com.alkemy.wallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    // Relaciones
    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private Person person; // Foreign key de la tabla user a la tabla person.

    // Constructores
    public User() {
    }

    public User(int id, String email, String password, Person person) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.person = person;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", person=" + person + "]";
    }

}
