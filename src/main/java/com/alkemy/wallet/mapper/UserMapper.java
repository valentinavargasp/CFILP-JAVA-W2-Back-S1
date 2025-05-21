package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.alkemy.wallet.dto.UserDTO;
import com.alkemy.wallet.models.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "person", target = "fullName", qualifiedByName = "personToFullName")
    UserDTO toDTO(User user);

    @Named("personToFullName")
    static String personToFullName(com.alkemy.wallet.models.user.Person person) {
        return person == null ? null : person.getName() + " " + person.getLastName();
}

}

