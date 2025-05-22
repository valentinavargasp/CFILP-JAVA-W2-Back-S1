package com.alkemy.wallet.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.PersonDTO;
import com.alkemy.wallet.models.user.Person;

/**
 * Mapper para convertir entre la entidad Person y su DTO.
 * Permite hacer conversiones individuales o de listas completas.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {


    /**
     * Convierte una entidad Person en su DTO.
     */
    PersonDTO toDTO(Person person);

    /**
     * Convierte un DTO a una entidad Person.
     */
    @Mapping(target = "idPerson", ignore = true)
    Person toEntity(PersonDTO personDTO);

    /**
     * Convierte una lista de entidades Person a una lista de PersonDTO.
     */
    List<PersonDTO> toDTOList(List<Person> persons);
}
