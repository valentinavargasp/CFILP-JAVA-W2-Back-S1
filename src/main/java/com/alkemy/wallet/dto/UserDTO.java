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

}
