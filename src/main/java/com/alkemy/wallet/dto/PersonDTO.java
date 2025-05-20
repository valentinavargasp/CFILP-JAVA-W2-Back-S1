package com.alkemy.wallet.dto;

import lombok.Data;

/**
 * Clase DTO (Data Transfer Object) que representa a una persona.
 * Se utiliza para transferir datos entre la capa de presentación y la capa de servicio.
 */
@Data
public class PersonDTO {

    private int idPerson;
    private String name;
    private String lastName;
    private String address;
    private String location;
    private String province;
    private String phoneNumber;
    private int identityCard;
    private String dateBirth;
}
