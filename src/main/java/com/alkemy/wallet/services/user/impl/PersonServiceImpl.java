package com.alkemy.wallet.services.user.impl;

import com.alkemy.wallet.services.user.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alkemy.wallet.models.user.Person;

import com.alkemy.wallet.repository.user.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person savePerson(Person person) {
        // Check if the person already exists
        if (personRepository.findByIdentityCard(person.getIdentityCard()).isPresent()) {
            throw new IllegalArgumentException("Esta persona ya existe");
        }
        personRepository.save(person);
        return person;
    }

    @Override
    public Person editPerson(int id, Person newPersonData) {
        return personRepository.findById(id).map(person -> {
            if (newPersonData.getName() != null) {
                person.setName(newPersonData.getName());
            }
            if (newPersonData.getLastName() != null) {
                person.setLastName(newPersonData.getLastName());
            }
            if (newPersonData.getAddress() != null) {
                person.setAddress(newPersonData.getAddress());
            }
            if (newPersonData.getLocation() != null) {
                person.setLocation(newPersonData.getLocation());
            }
            if (newPersonData.getPhoneNumber() != null) {
                person.setPhoneNumber(newPersonData.getPhoneNumber());
            }
            if (newPersonData.getDateBirth() != null) {
                person.setDateBirth(newPersonData.getDateBirth());
            }
            return personRepository.save(person);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada con id " + id));
    }

    // eliminar persona
    @Override
    public void deletePersonById(int id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Cuenta no encontrada con id " + id);
        }
        personRepository.deleteById(id);
    }

    // buscadores
    @Override
    public Person findPersonById(int id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con id " + id));
    }

    @Override
    public Person findPersonByName(String name) {
        return personRepository.findByLastName(name)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con nombre " + name));
    }

    @Override
    public Person findPersonByLastName(String lastName) {
        return personRepository.findByLastName(lastName)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con apellido: " + lastName));
    }

    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con teléfono: " + phoneNumber));
    }

    @Override
    public List<Person> findPersonsByLocation(String location) {
        return personRepository.findByLocationIgnoreCase(location);
    }

    @Override
    public Person findPersonByAddress(String address) {
        return personRepository.findByAddress(address)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con dirección: " + address));
    }

    @Override
    public Person findPersonByIdentityCard(int identityCard) {
        return personRepository.findByIdentityCard(identityCard)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Persona no encontrada con número de identificación: " + identityCard));
    }

    @Override
    public Person findPersonByDateBirth(String dateBirth) {
        return personRepository.findByDateBirth(dateBirth)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Persona no encontrada con fecha de nacimiento: " + dateBirth));

    }

}
