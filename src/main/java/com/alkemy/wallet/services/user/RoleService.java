package com.alkemy.wallet.services.user;


import com.alkemy.wallet.models.user.Role;

public interface RoleService {

    public Role getRoleByName(String name);
    public Role getRoleById(int id);
    public void saveRole(Role role);
    public void deleteRoleById(int id);
    public void editRole(Role role);

}
