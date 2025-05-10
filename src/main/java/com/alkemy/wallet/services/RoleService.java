package com.alkemy.wallet.services;



public interface RoleService {

    public Role getRoleByName(String name);
    public Role getRoleById(int id);
    public void saveRole(Role role);
    public void deleteRoleById(int id);
    public void editRole(Role role);

}
