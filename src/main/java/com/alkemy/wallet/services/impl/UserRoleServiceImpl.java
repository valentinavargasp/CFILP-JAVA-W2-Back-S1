package com.alkemy.wallet.services.impl;

import com.alkemy.wallet.models.Role;
import com.alkemy.wallet.models.User;
import com.alkemy.wallet.repository.RoleRepository;
import com.alkemy.wallet.repository.UserRepository;
import com.alkemy.wallet.repository.UserRoleRepository;
import com.alkemy.wallet.services.UserRoleService;
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



    //TODO:   implementar la logica necesaria para la interaccion entre la tabla rol y  user.

    @Override
    public List<User> getAllUsersByRole(String role) {
        return List.of();
    }

    @Override
    public List<User> getAllUsersByRoleId(int id) {
        return List.of();
    }

    @Override
    public List<Role> getAllRolesByUserId(int id) {
        return List.of();
    }

    @Override
    public void saveUserRole(int userId, int roleId) {

    }

    @Override
    public void deleteUserRole(int userId, int roleId) {

    }

}
