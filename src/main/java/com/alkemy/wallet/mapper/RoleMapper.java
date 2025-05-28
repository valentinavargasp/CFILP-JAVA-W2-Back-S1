package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alkemy.wallet.dto.RoleDTO;
import com.alkemy.wallet.models.user.Role;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "userCount", expression = "java(role.getRoles() != null ? role.getRoles().size() : 0)")
    RoleDTO toDTO(Role role);

}