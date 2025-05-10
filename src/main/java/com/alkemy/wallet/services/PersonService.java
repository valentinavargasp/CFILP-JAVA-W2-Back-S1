package com.alkemy.wallet.services;

import com.alkemy.wallet.models.Person;

public interface PersonService {
    //TODO: metodos para agregar, editar, borrar y  buscar personas(por id, documento, apellido?)

    public void savePerson(Person person);

    public void editPerson(Person person);

    public void deletePersonById(int id);

    public Person findPersonById(int id);
    //todo:  buscar personas por apellido, dni.
}
