package com.alkemy.wallet.controllers;

import java.util.List;

import com.alkemy.wallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.wallet.models.user.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // GET de todos los usuarios
    // http://localhost:8080/api/user
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers(); // Si no hay usuarios, se lanzará una excepción que será manejada por el GlobalExceptionHandler
        return ResponseEntity.ok(users); //si hay usuarios, se devuelve un 200 OK
    }

    // GET de un usuario por ID
    // http://localhost:8080/api/user/1
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id); // Si el usuario no existe, se lanzará una excepción que será manejada por el GlobalExceptionHandler
        return ResponseEntity.ok(user); //si el usuario existe, se devuelve un 200 OK
    }

    // POST de un usuario
    // http://localhost:8080/api/user
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user); //Si hay algún error de validación, lo manejará el GlobalExceptionHandler
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // Si todo va bien devuelve un 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userData) {
        User updated = userService.editUserById(id, userData); // Si el usuario no existe EntityNotFoundException manejada por el GlobalExceptionHandler
        return ResponseEntity.ok(updated); // Devuelve un 200 OK
    }

    // DELETE de un usuario por ID
    // http://localhost:8080/api/user/1 con metodo delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id); //Si el usuario no existe se lanzará EntityNotFoundException
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content
    }

}
