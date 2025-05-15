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



    @Override
    public List<User> getAllUsersByRole(String role) {
        if(role == null ){
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }
        try {
            return userRepository.findByRoles_RoleName(role);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsersByRoleId(int id) {
        try {
            return userRepository.findByRoles_Id(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> getAllRolesByUserId(int id) {
       try {
           return userRoleRepository.findAllByUserId(id);
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public void saveUserRole(int userId, int roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con id: " + roleId));
        try {
            userRoleRepository.save(new UserRole(user, role));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserRole(int userId, int roleId) {
        try {
            UserRole userRole = userRoleRepository.findByUserIdAndRoleId(userId, roleId);
            if (userRole != null) {
                userRoleRepository.delete(userRole);
            }else {
                throw new IllegalArgumentException("No se puede eliminar. Rol no encontrado con id: " + roleId);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
