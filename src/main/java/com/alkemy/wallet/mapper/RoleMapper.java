package com.alkemy.wallet.mapper;

import org.mapstruct.Mapper;

import com.alkemy.wallet.dto.RoleDTO;
import com.alkemy.wallet.models.user.Role;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);

    Role toEntity(RoleDTO dto);
}