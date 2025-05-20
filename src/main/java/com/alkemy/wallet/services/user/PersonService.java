package com.alkemy.wallet.services.user;

import com.alkemy.wallet.dto.PersonDTO;
import java.util.List;

public interface PersonService {

    public PersonDTO savePerson(PersonDTO personDTO); // guarda

    public PersonDTO editPerson(int id, PersonDTO personDTO); // edita

    public void deletePersonById(int id); // elimina

    public PersonDTO findPersonById(int id);

    public PersonDTO findPersonByName(String name);

    public PersonDTO findPersonByLastName(String lastName);

    public PersonDTO findPersonByPhoneNumber(String phoneNumber);

    List<PersonDTO> findPersonsByLocation(String location);

    public PersonDTO findPersonByAddress(String address);

    public PersonDTO findPersonByIdentityCard(int identityCard);

    public PersonDTO findPersonByDateBirth(String dateBirth);

    public List<PersonDTO> getAllPersons(); // NUEVO: método para listar todas las personas

}
