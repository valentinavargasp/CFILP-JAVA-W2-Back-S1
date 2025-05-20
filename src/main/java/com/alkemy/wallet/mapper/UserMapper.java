package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.alkemy.wallet.dto.UserDTO;
import com.alkemy.wallet.models.user.User;

/**
 * Mapper para convertir entre la entidad User y su DTO.
 * Usa MapStruct para generar automáticamente el código de conversión.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDTO toDTO(User user);

    // Mapea UserDTO -> User (incluye también el Person mapeado automáticamente)
    User toEntity(UserDTO userDTO);

}
