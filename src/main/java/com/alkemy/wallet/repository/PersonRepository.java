package com.alkemy.wallet.repository;

import com.alkemy.wallet.models.Person;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByName(String name);
}
