package com.alkemy.wallet.services.user.impl;

import java.util.List;

import com.alkemy.wallet.services.user.UserService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User editUserById(int id, User newUserData) {


            return userRepository.findById(id).map(user -> {

            user.setEmail(newUserData.getEmail());
            user.setPassword(newUserData.getPassword());
            user.setPerson(newUserData.getPerson());
            user.setAccounts(newUserData.getAccounts());
            user.setUserRoles(newUserData.getUserRoles());

            return userRepository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));

    };

    @Override
    public void deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Usuario no encontrado con id " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
