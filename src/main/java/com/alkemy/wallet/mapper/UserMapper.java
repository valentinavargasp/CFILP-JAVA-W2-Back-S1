package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.alkemy.wallet.dto.UserDTO;
import com.alkemy.wallet.models.user.User;

/**
 * Mapper para convertir entre la entidad User y su DTO.
 * Usa MapStruct para generar automáticamente el código de conversión.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    //Instancia autogenerada para inyección o uso manual
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapea User -> UserDTO, con un campo personalizado para nombre completo
    @Mapping(source = "person", target = "fullName", qualifiedByName = "personToFullName")
    UserDTO toDTO(User user);

    //Mapea UserDTO -> User (incluye también el Person mapeado automáticamente)
    User toEntity(UserDTO userDTO);

    //Función auxiliar para convertir un objeto Person a un String con el nombre completo
    @Named("personToFullName")
    static String personToFullName(com.alkemy.wallet.models.user.Person person) {
        return person == null ? null : person.getName() + " " + person.getLastName();
    }

}
