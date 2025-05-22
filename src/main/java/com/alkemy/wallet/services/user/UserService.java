package com.alkemy.wallet.services.user;

import com.alkemy.wallet.dto.UserDTO;

import java.util.List;

public interface UserService {

    /**
     * Guarda un nuevo usuario a partir de un DTO.
     */
    public UserDTO getUserById(int id);
    public List<UserDTO> getAllUsers();
    public void deleteUserById(int id);
    public UserDTO editUserById(int id, UserDTO newUserData);
}
