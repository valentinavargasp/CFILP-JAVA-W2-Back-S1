package com.alkemy.wallet.models;




import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    @Column(name="id_user")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    // Relación uno a uno con la entidad Person
    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    private Person person;

    //Relación con Account. Para ver las cuentas de un usuario
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Account> accounts;

    // Relación con UserRole. Para ver los roles de un usuario
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserRole> userRoles;
    private Person person2; // Foreign key de la tabla user a la tabla person.

    // Constructor
    public User() {
    }

    public User(String email, String password, Person person) {
        this.email = email;
        this.password = password;
        this.person = person;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
    }
    
}
