package com.alkemy.wallet.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para representar un usuario.
 * Se utiliza para enviar datos al frontend sin exponer la entidad completa.
 */
@Data
public class UserDTO {

    private int id;
    private String username;
    private String email;
    private String fullName; //Podríamos formarlo acá a partir de la entidad persona
    
}
