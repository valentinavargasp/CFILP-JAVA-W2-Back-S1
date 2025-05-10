package com.alkemy.wallet.repository;

import com.alkemy.wallet.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {


}
