package com.alkemy.wallet.services.user;


import java.util.List;

import com.alkemy.wallet.models.user.Role;

public interface RoleService {

    public Role getRoleByName(String name);
    public Role getRoleById(int id);
    public Role saveRole(Role role);
    public Role deleteRoleById(int id);
    public Role editRole(int id,Role role);
    public List<Role> getAllRoles();    // Agregado método para listar todos los roles

}
