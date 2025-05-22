package com.alkemy.wallet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Clase DTO (Data Transfer Object) que representa a una persona.
 * Se utiliza para transferir datos entre la capa de presentación y la capa de
 * servicio.
 */
@Data
public class PersonDTO {

    private int idPerson;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;
    @NotBlank(message = "La dirección no puede estar vacía")
    private String address;
    @NotBlank(message = "La localidad no puede estar vacía")
    private String location;
    @NotBlank(message = "La provincia no puede estar vacía")
    private String province;
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private String phoneNumber;
    @NotBlank(message = "El número de documento no puede estar vacío")
    private int identityCard;
    @NotBlank(message = "La fecha de nacimiento no puede estar vacía")
    private String dateBirth;

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(int identityCard) {
        this.identityCard = identityCard;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

}
