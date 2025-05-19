package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
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


    UserDTO toDTO(User user);

    //Mapea UserDTO -> User (incluye también el Person mapeado automáticamente)
    User toEntity(UserDTO userDTO);
    


}
