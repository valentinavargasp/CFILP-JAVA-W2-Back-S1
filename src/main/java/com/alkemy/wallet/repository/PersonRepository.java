package com.alkemy.wallet.repository;

import com.alkemy.wallet.models.Person;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByIdentityCard(int identityCard);

    Optional<Person> findByLastName(String lastName);
}