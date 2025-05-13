package com.alkemy.wallet.repository.user;



import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.wallet.models.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
