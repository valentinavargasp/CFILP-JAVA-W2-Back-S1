package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.alkemy.wallet.dto.PersonDTO;
import com.alkemy.wallet.dto.UserCreateDTO;
import com.alkemy.wallet.dto.UserDTO;
import com.alkemy.wallet.models.user.Person;
import com.alkemy.wallet.models.user.User;

/**
 * Mapper para convertir entre la entidad User y su DTO.
 * Usa MapStruct para generar automáticamente el código de conversión.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    //Por ahora no se usa, los roles se manejan en jwt
    //@Mapping(target = "role", expression = "java(user.getUserRoles() != null && !user.getUserRoles().isEmpty() ? user.getUserRoles().get(0).getRole().getRoleName() : \"Sin rol\")")
    // Mapea User -> UserDTO (incluye también el Person mapeado automáticamente)
    @Mapping(target = "person", source = "person")
    UserDTO toDTO(User user);

    // Mapea UserDTO -> User (incluye también el Person mapeado automáticamente)
    User toEntity(UserDTO userDTO);


    //USER CREATE DTO
    UserCreateDTO toCreateDTO(User user);
    // Mapea UserCreateDTO -> User (incluye también el Person mapeado automáticamente)

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // Se setea manualmente luego
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "userRoles", ignore = true)
    User toEntity(UserCreateDTO userCreateDTO);
    // Mapea User -> UserCreateDTO (incluye también el Person mapeado automáticamente)

    Person toEntity(PersonDTO person);
}
