package com.alkemy.wallet.controllers;

import com.alkemy.wallet.models.user.Role;
import com.alkemy.wallet.services.user.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Gestión de roles del sistema")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Listar todos los roles")
    @ApiResponse(responseCode = "200", description = "Lista de roles")
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @Operation(summary = "Obtener un rol por ID")
    @ApiResponse(responseCode = "200", description = "Rol encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @Operation(summary = "Crear un nuevo rol")
    @ApiResponse(responseCode = "201", description = "Rol creado")
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role savedRole = roleService.saveRole(role);
        return ResponseEntity.status(201).body(savedRole);
    }

    @Operation(summary = "Actualizar un rol existente")
    @ApiResponse(responseCode = "200", description = "Rol actualizado")
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role role) {
        Role updatedRole = roleService.editRole(id, role);
        return ResponseEntity.ok(updatedRole);
    }

    @Operation(summary = "Eliminar un rol por ID")
    @ApiResponse(responseCode = "204", description = "Rol eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }
}

