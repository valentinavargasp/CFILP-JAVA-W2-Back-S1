package com.alkemy.wallet.services;

import com.alkemy.wallet.models.user.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public User getUserById(int id);
    public List<User> getAllUsers();
    public void deleteUserById(int id);
    public User editUserById(int id, User newUserData);
}
