package com.alkemy.wallet.controllers;

import com.alkemy.wallet.models.user.Role;
import com.alkemy.wallet.models.user.User;
import com.alkemy.wallet.services.user.UserRoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@Tag(name = "User Roles", description = "Asignación y consulta de roles por usuario")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Operation(summary = "Obtener todos los usuarios con un rol específico")
    @GetMapping("/role/{roleName}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String roleName) {
        List<User> users = userRoleService.getAllUsersByRole(roleName);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Obtener todos los usuarios por ID de rol")
    @GetMapping("/role/id/{roleId}")
    public ResponseEntity<List<User>> getUsersByRoleId(@PathVariable int roleId) {
        List<User> users = userRoleService.getAllUsersByRoleId(roleId);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Obtener todos los roles asignados a un usuario")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Role>> getRolesByUserId(@PathVariable int userId) {
        List<Role> roles = userRoleService.getAllRolesByUserId(userId);
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "Asignar un rol a un usuario")
    @PostMapping
    public ResponseEntity<Void> assignRoleToUser(@RequestParam int userId, @RequestParam int roleId) {
        userRoleService.saveUserRole(userId, roleId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Eliminar un rol de un usuario")
    @DeleteMapping
    public ResponseEntity<Void> removeRoleFromUser(@RequestParam int userId, @RequestParam int roleId) {
        userRoleService.deleteUserRole(userId, roleId);
        return ResponseEntity.noContent().build();
    }
}

