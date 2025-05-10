package com.alkemy.wallet.services.impl;

import java.util.List;

import com.alkemy.wallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.wallet.models.User;
import com.alkemy.wallet.repository.UserRepository;

/*todo: creo que podemos implementar la logica de userrole aca agregando el repositorio correspondiente*/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
