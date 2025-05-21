package com.alkemy.wallet.dto;

import lombok.Data;

@Data
public class UserDTO {

    private int id;
    private String username;
    private String email;
    private String fullName; //Podríamos formarlo acá a partir de la entidad persona
    
}
