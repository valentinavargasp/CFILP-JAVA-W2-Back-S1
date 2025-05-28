package com.alkemy.wallet.controllers;

import com.alkemy.wallet.dto.RoleDTO;
import com.alkemy.wallet.mapper.RoleMapper;
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

    private final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @Operation(summary = "Listar todos los roles")
    @ApiResponse(responseCode = "200", description = "Lista de roles")
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        List<RoleDTO> roleDTOs = roles.stream()
                .map(roleMapper::toDTO)
                .toList();
        return ResponseEntity.ok(roleDTOs);
    }

    @Operation(summary = "Obtener un rol por ID")
    @ApiResponse(responseCode = "200", description = "Rol encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        RoleDTO roleDTO = roleMapper.toDTO(role);
        return ResponseEntity.ok(roleDTO);
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
