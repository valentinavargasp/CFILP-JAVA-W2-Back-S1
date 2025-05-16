package com.alkemy.wallet.repository.user;

import com.alkemy.wallet.models.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    // Devuelve los roles asociados a un usuario
    List<UserRole> findAllByUser_Id(int id);

    // Devuelve una asociación específica entre user y rol
    UserRole findByUser_IdAndRole_Id(int userId, int roleId);


}
