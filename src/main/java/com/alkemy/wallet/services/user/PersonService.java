package com.alkemy.wallet.services.user;

import com.alkemy.wallet.models.user.Person;
import java.util.List;


public interface PersonService {


    public Person savePerson(Person person);  //guarda
    public Person editPerson(int id, Person person);  //edita
    public void deletePersonById(int id);  //elimina
    public Person findPersonById(int id);
    public Person findPersonByName(String name);
    public Person findPersonByLastName(String lastName);
    public Person findPersonByPhoneNumber(String phoneNumber);
    List<Person> findPersonsByLocation(String location);
    public Person findPersonByAddress(String address);
    public Person findPersonByIdentityCard(int identityCard);
    public Person findPersonByDateBirth(String dateBirth);

}
