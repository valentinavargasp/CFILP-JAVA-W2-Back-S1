package com.alkemy.wallet.services.user;

import com.alkemy.wallet.models.user.Role;
import com.alkemy.wallet.models.user.User;

import java.util.List;

//USER_ROLE  es una tabla que maneja relaciones.

public interface UserRoleService {

  // metodos para agregar, borrar y  buscar roles de usuario

    public List<User> getAllUsersByRole(String role); //busca users por nombre de rol
    public List<User> getAllUsersByRoleId(int id); // busca users por id de rol
    public List<Role> getAllRolesByUserId(int id); //busca los roles de un usuario por id del usuario


    public void saveUserRole(int userId, int roleId); // guarda un nuevo rol para un usuario
    public void deleteUserRole(int userId, int roleId); // elimina un rol de un usuario
}
