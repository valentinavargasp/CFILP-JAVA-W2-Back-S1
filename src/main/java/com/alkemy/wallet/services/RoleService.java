package com.alkemy.wallet.services;


import com.alkemy.wallet.models.Role;

public interface RoleService {

    public Role getRoleByName(String name);
    public Role getRoleById(int id);
    public void saveRole(Role role);
    public void deleteRoleById(int id);
    public void editRole(Role role);

}
