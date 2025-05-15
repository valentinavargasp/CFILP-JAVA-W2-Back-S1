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
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

    //Constructores
    public UserRole(){

    }


    public UserRole(User user, Role role){
        this.user = user; 
        this.role = role;

    }


    @Override
    public String toString(){
        return "UserRole [id =" + id + ", user = " + user.getId() + ", role = " + role + "]";
    }
    
}
