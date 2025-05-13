package com.alkemy.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alkemy.wallet.models.Person;
import com.alkemy.wallet.repository.PersonRepository;
import com.alkemy.wallet.services.PersonService;

import jakarta.persistence.EntityNotFoundException;

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void savePerson(Person person) {
        // Check if the person already exists
        if (personRepository.findByIdentityCard(person.getIdentityCard()).isPresent()) {
            throw new IllegalArgumentException("Esta persona ya existe");
        }
        personRepository.save(person);
    }

    @Override
    public void editPerson(Person person) {
        if (!personRepository.existsById(person.getIdPerson())) {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + person.getIdPerson());
        }
        personRepository.save(person);
    }

    @Override
    public void deletePersonById(int id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Persona no encontrada para eliminar con ID: " + id);
        }
        personRepository.deleteById(id);
    }

    @Override
    public Person findPersonById(int id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + id));
    }

    @Override
    public Person findPersonByIdentityCard(int identityCard) {
        return personRepository.findByIdentityCard(identityCard)
        .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con DNI: " + identityCard));
    }

    @Override
    public Person findPersonByLastName(String lastName) {
        return personRepository.findByLastName(lastName).orElse(null);
    }

}
