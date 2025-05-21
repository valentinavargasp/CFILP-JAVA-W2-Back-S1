package com.alkemy.wallet.models.user;




import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Lazy;

import com.alkemy.wallet.models.account.Account;
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

    @Column(name = "username")
    private String username;

    // Relación uno a uno con la entidad Person
    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person", nullable = true)
    private Person person;

    //Relación con Account. Para ver las cuentas de un usuario
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Account> accounts;

    // Relación con UserRole. Para ver los roles de un usuario
    @OneToMany(mappedBy = "user")
    @Lazy(value = false)
    @JsonIgnore
    private Set<UserRole> userRoles;

}
