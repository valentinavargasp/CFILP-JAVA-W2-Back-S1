package com.alkemy.wallet.services.user.impl;

import com.alkemy.wallet.services.user.PersonService;
import lombok.RequiredArgsConstructor;

import com.alkemy.wallet.dto.PersonDTO;
import com.alkemy.wallet.mapper.PersonMapper;
import com.alkemy.wallet.models.user.Person;

import com.alkemy.wallet.repository.user.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    /*
     * Método para listar todas las personas
     */
    @Override
    public List<PersonDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return personMapper.toDTOList(persons);
    }

    /*
     * Método para guardar una persona
     */
    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        // Check if the person already exists
        if (personRepository.findByIdentityCard(personDTO.getIdentityCard()).isPresent()) {
            throw new IllegalArgumentException("Esta persona ya existe");
        }
        Person person = personMapper.toEntity(personDTO);
        return personMapper.toDTO(personRepository.save(person));
    }

    /*
     * Método para editar una persona
     */
    @Override
    public PersonDTO editPerson(int id, PersonDTO personDTO) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con id " + id));

        // Actualizar campos
        if (personDTO.getName() != null)
            person.setName(personDTO.getName());
        if (personDTO.getLastName() != null)
            person.setLastName(personDTO.getLastName());
        if (personDTO.getAddress() != null)
            person.setAddress(personDTO.getAddress());
        if (personDTO.getLocation() != null)
            person.setLocation(personDTO.getLocation());
        if (personDTO.getProvince() != null)
            person.setProvince(personDTO.getProvince());
        if (personDTO.getPhoneNumber() != null)
            person.setPhoneNumber(personDTO.getPhoneNumber());
        if (personDTO.getDateBirth() != null)
            person.setDateBirth(personDTO.getDateBirth());

        return personMapper.toDTO(personRepository.save(person));
    }

    /*
     * Método para eliminar una persona
     */
    @Override
    public void deletePersonById(int id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Cuenta no encontrada con id " + id);
        }
        personRepository.deleteById(id);
    }

    /*
     * Método para buscar una persona por id
     */
    @Override
    public PersonDTO findPersonById(int id) {
        return personRepository.findById(id)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con id " + id));
    }

    /*
     * Método para buscar una persona por nombre
     */
    @Override
    public PersonDTO findPersonByName(String name) {
        return personRepository.findByName(name)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con nombre " + name));
    }

    /*
     * Método para buscar una persona por apellido
     */
    @Override
    public PersonDTO findPersonByLastName(String lastName) {
        return personRepository.findByLastName(lastName)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con apellido: " + lastName));
    }

    /*
     * Método para buscar una persona por teléfono
     */
    @Override
    public PersonDTO findPersonByPhoneNumber(String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con teléfono: " + phoneNumber));
    }

    /*
     * Método para buscar una persona por dirección
     */
    @Override
    public List<PersonDTO> findPersonsByLocation(String location) {
        return personRepository.findByLocationIgnoreCase(location)
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * Método para buscar una persona por dirección
     */
    @Override
    public PersonDTO findPersonByAddress(String address) {
        return personRepository.findByAddress(address)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con dirección: " + address));
    }

    /*
     * Método para buscar una persona por número de identificación
     */
    @Override
    public PersonDTO findPersonByIdentityCard(int identityCard) {
        return personRepository.findByIdentityCard(identityCard)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Persona no encontrada con número de identificación: " + identityCard));
    }

    /*
     * Método para buscar una persona por fecha de nacimiento
     */
    @Override
    public PersonDTO findPersonByDateBirth(String dateBirth) {
        return personRepository.findByDateBirth(dateBirth)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Persona no encontrada con fecha de nacimiento: " + dateBirth));
    }

}
