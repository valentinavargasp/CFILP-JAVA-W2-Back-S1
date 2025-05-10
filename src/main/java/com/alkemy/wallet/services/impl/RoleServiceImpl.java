package com.alkemy.wallet.services.impl;

import com.alkemy.wallet.models.Role;
import com.alkemy.wallet.repository.RoleRepository;
import com.alkemy.wallet.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByRoleName(name)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + name));
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }

    //TODO:  implementar la logica del metodo editRole.
    public void editRole(Role role) {
        roleRepository.save(role);
    }
}
