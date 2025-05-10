package com.alkemy.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.alkemy.wallet.models.Person;
import com.alkemy.wallet.services.PersonService;
import org.springframework.web.bind.annotation.RequestParam;


/*todo: implementar las rutas para person Controller*/

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person/id/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable int id) {
        try {
            Person person = personService.findPersonById(id);
            if (person != null) {
                return ResponseEntity.ok(person);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/person/name/{name}")
    public  ResponseEntity<Person> findPersonByName(@PathVariable String name) {
        try {
            Person person = personService.findPersonByName(name);
            if (person != null) {
                return ResponseEntity.ok(person);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

}
