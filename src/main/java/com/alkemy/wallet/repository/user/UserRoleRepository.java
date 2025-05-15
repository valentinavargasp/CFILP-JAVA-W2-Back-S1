package com.alkemy.wallet.repository.user;

import com.alkemy.wallet.models.user.Role;
import com.alkemy.wallet.models.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {


    List<Role> findAllByUserId(int id);

    UserRole findByUserIdAndRoleId(int userId, int roleId);


}
