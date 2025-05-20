package com.alkemy.wallet.dto;

import lombok.Data;

/**
 * CreateUserDTO. solo lo usaremos para la creacion del usuario ya que acepta el campo contraseña. 
 * 
 */

@Data
public class UserCreateDTO {

    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
