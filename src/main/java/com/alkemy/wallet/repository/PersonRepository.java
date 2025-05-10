package com.alkemy.wallet.repository;

import com.alkemy.wallet.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
}
