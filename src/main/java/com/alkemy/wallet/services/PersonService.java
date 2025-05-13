package com.alkemy.wallet.services;

import com.alkemy.wallet.models.Person;

public interface PersonService {

    public void savePerson(Person person);

    public void editPerson(Person person);

    public void deletePersonById(int id);

    public Person findPersonById(int id);

    public Person findPersonByIdentityCard(int identityCard);

    public Person findPersonByLastName(String lastName);

}
