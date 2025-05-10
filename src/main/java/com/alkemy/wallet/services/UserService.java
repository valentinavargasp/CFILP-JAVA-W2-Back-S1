package com.alkemy.wallet.services;

import com.alkemy.wallet.models.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public User getUserById(int id);
    public List<User> getAllUsers();

    //TODO: metodos para borrar usuario, editar usuario, hay que implementarlos en UserServiceImpl
    public User editUserById(int id, User newUserData);
    public void deleteUserById(int id);
}
