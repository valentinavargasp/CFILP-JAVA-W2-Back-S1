package com.alkemy.wallet.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alkemy.wallet.dto.PersonDTO;
import com.alkemy.wallet.models.user.Person;

/**
 * Mapper para convertir entre la entidad Person y su DTO.
 * Permite hacer conversiones individuales o de listas completas.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    /**
     * Instancia singleton de la interfaz PersonMapper, creada usando la fábrica
     * Mappers de MapStruct.
     * Esta instancia proporciona métodos de mapeo para convertir entre entidades
     * Person y sus DTOs.
     */
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    /**
     * Convierte una entidad Person en su DTO.
     */
    PersonDTO toDTO(Person person);

    /**
     * Convierte un DTO a una entidad Person.
     */
    Person toEntity(PersonDTO personDTO);

    /**
     * Convierte una lista de entidades Person a una lista de PersonDTO.
     */
    List<PersonDTO> toDTOList(List<Person> persons);
}
