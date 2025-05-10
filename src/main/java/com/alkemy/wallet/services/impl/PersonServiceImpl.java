package com.alkemy.wallet.services.impl;

import com.alkemy.wallet.models.Person;
import com.alkemy.wallet.repository.PersonRepository;
import com.alkemy.wallet.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PersonServiceImpl implements com.alkemy.wallet.services.PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findPersonById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person findPersonByName(String name){
        return personRepository.findByName(name).orElse(null);
    }
    
}
