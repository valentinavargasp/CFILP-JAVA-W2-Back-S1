package com.alkemy.wallet.repository.user;



import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByRoles_RoleName(String role);

    List<User> findByRoles_Id(int id);
}
