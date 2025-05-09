package com.alkemy.wallet.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
