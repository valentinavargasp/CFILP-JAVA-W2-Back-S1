package com.alkemy.wallet.services.user.impl;

import com.alkemy.wallet.models.user.Role;
import com.alkemy.wallet.repository.user.RoleRepository;
import com.alkemy.wallet.services.user.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityNotFoundException;



@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con id: " + id));
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByRoleName(name)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado: " + name));
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public void deleteRoleById(int id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Rol no encontrado con id " + id);
        }
        roleRepository.deleteById(id);
    }

    // TODO: implementar la logica del metodo editRole.
    public void editRole(Role role) {
        if (!roleRepository.existsById(role.getId())) {
            throw new EntityNotFoundException("No se puede editar. Rol no encontrado con id " + role.getId());
        }
        roleRepository.save(role);
    }
}
