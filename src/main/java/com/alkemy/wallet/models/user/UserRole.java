package com.alkemy.wallet.models.user;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user_role")
    private int id;

    //Relaciones
    //Relación con User
    @ManyToOne //Un usuario puede tener muchos roles
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @JsonBackReference
    private User user;

    //Relación con Role
    @ManyToOne //Un rol puede tener muchos usuarios
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    private Role role;

}
