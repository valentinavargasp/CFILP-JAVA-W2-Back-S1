package com.alkemy.wallet.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para representar un usuario.
 * Se utiliza para enviar datos al frontend sin exponer la entidad completa.
 */
@Data
@Getter
@Setter
public class UserDTO {

    private int id;
    private String username;

    // Persona asociada
    private String name;
    private String lastName;
    private String address;
    private String location;
    private String province;
    private String phoneNumber;
    private int identityCard;
    private String dateBirth;
    private String email;

     // Roles
    private List<String> roles;

    // Cuentas
    private List<AccountDTO> accounts;
    
}
