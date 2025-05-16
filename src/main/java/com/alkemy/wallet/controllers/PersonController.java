package com.alkemy.wallet.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.alkemy.wallet.models.user.Person;
import com.alkemy.wallet.services.user.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Personas", description = "Gestión de personas del sistema")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Listar todas las personas")
    @ApiResponse(responseCode = "200", description = "Lista de personas")
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @Operation(summary = "Obtener una persona por ID")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable int id) {
        return ResponseEntity.ok(personService.findPersonById(id));
    }

    @Operation(summary = "Crear nueva persona")
    @ApiResponse(responseCode = "201", description = "Persona creada")
    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        Person saved = personService.savePerson(person);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Actualizar una persona existente")
    @ApiResponse(responseCode = "200", description = "Persona actualizada")
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person newPersonData) {
        return ResponseEntity.ok(personService.editPerson(id, newPersonData));
    }

    @Operation(summary = "Eliminar persona por ID")
    @ApiResponse(responseCode = "204", description = "Persona eliminada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint de búsqueda de personas por diferentes criterios

    @Operation(summary = "Buscar persona por nombre")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @GetMapping("/search/name/{name}")
    public ResponseEntity<Person> findPersonByName(@PathVariable String name) {
        return ResponseEntity.ok(personService.findPersonByName(name));
    }

    @Operation(summary = "Buscar persona por apellido")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @GetMapping("/search/lastName/{lastName}")
    public ResponseEntity<Person> findPersonByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(personService.findPersonByLastName(lastName));
    }

    @Operation(summary = "Buscar persona por número de teléfono")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @GetMapping("/search/phoneNumber/{phoneNumber}")
    public ResponseEntity<Person> findPersonByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(personService.findPersonByPhoneNumber(phoneNumber));
    }

    @Operation(summary = "Buscar persona por Ubicación")
    @ApiResponse(responseCode = "200", description = "Lista de personas encontradas")
    @GetMapping("/search/location")
    public ResponseEntity<List<Person>> getPersonsByLocation(@RequestParam String location) {
        List<Person> persons = personService.findPersonsByLocation(location);
        return ResponseEntity.ok(persons);
    }

    @Operation(summary = "Buscar persona por dirección")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @GetMapping("/search/address/{address}")
    public ResponseEntity<Person> findPersonByAddress(@PathVariable String address) {
        return ResponseEntity.ok(personService.findPersonByAddress(address));
    }

    @Operation(summary = "Buscar persona por número de documento")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @GetMapping("/search/identityCard/{identityCard}")
    public ResponseEntity<Person> findPersonByIdentityCard(@PathVariable int identityCard) {
        return ResponseEntity.ok(personService.findPersonByIdentityCard(identityCard));
    }

    @Operation(summary = "Buscar persona por fecha de nacimiento")
    @ApiResponse(responseCode = "200", description = "Persona encontrada")
    @GetMapping("/search/dateBirth/{dateBirth}")
    public ResponseEntity<Person> findPersonByDateBirth(@PathVariable String dateBirth) {
        return ResponseEntity.ok(personService.findPersonByDateBirth(dateBirth));
    }

}
