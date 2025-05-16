package com.alkemy.wallet.services.user.impl;

import com.alkemy.wallet.models.user.Role;
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.models.user.UserRole;
import com.alkemy.wallet.repository.user.RoleRepository;
import com.alkemy.wallet.repository.user.UserRepository;
import com.alkemy.wallet.repository.user.UserRoleRepository;
import com.alkemy.wallet.services.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

/**
     * Devuelve todos los usuarios que tienen un rol específico por nombre.
     * Ejemplo: "ADMIN"
     * Lanza excepción si el rol es nulo o vacío.
     */
    @Override
    public List<User> getAllUsersByRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("El rol no puede ser nulo o vacío");
        }
        return userRepository.findByUserRoles_Role_RoleName(role);
    }

/**
     * Devuelve todos los usuarios que tienen un rol específico por ID.
     */
    @Override
    public List<User> getAllUsersByRoleId(int id) {
        return userRepository.findByUserRoles_Role_Id(id);
    }

        /**
     * Devuelve todos los roles que tiene un usuario, accediendo por su ID.
     * Mapea la relación desde UserRole para devolver una lista de objetos Role.
     */
@Override
public List<Role> getAllRolesByUserId(int id) {
    return userRoleRepository.findAllByUser_Id(id).stream()
        .map(UserRole::getRole)
        .toList();
}

    /**
     * Asigna un rol a un usuario. Verifica que existan ambos antes de guardar.
     */
    @Override
    public void saveUserRole(int userId, int roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con id: " + roleId));

        userRoleRepository.save(new UserRole(user, role));
    }

    /**
     * Elimina una asignación de rol de un usuario.
     * Si no existe esa relación, lanza una excepción.
     */
    @Override
    public void deleteUserRole(int userId, int roleId) {
        UserRole userRole = userRoleRepository.findByUser_IdAndRole_Id(userId, roleId);
        if (userRole != null) {
            userRoleRepository.delete(userRole);
        } else {
            throw new IllegalArgumentException("No se puede eliminar. Rol no encontrado con id: " + roleId);
        }
    }
}

