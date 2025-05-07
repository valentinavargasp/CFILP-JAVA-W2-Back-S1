package com.alkemy.wallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int id;

    @Column(name = "id_user")
    private int idUser; 
    
    @Column(name = "id_role")
    private int idRole;

    //Constructores

    public UserRole(){

    }

    public UserRole( int id, int user, int rol){

        this.id= id;
        this.idUser = user; 
        this.idRole = rol;

    }

    @Override
    public String toString(){
        return "UserRole [id =" + id + ", idUser = " + idUser + ", idRole = " + idRole + "]";
    }
    
}
