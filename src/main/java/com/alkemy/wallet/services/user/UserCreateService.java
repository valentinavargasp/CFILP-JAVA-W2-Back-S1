package com.alkemy.wallet.services.user;


import com.alkemy.wallet.dto.UserCreateDTO;

public interface UserCreateService {

    /**
     * Guarda un nuevo usuario a partir de un DTO.
     */

    public UserCreateDTO saveUser(UserCreateDTO userDTO);



}
