package com.maksim.Lab3.externalInterface.Controllers;

import DtoMappers.CatDtoMapper;
import DtoMappers.PersonDtoMapper;
import Dtos.CatDto;
import Dtos.PersonDto;
import Models.Cat;
import Models.Person;
import com.maksim.Lab3.externalInterface.RequestsMappers.CatRequestsMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.maksim.Lab3.persons.Services.IPersonService;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/persons")
@ComponentScan(basePackages = "com.maksim.Lab3.persons.Services")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<PersonDto>> getPersonById(@PathVariable UUID id) {
        return personService.getPersonById(id)
                .thenApply(person -> {
                    if (person == null) {
                        return ResponseEntity.notFound().build();
                    }
                    PersonDto personDto = PersonDtoMapper.mapToDto(person);
                    return ResponseEntity.ok(personDto);
                });
    }

    @PostMapping
    public ResponseEntity<Void> addPerson(@RequestBody PersonDto personDto) {
        Person person = PersonDtoMapper.mapToModel(personDto);
        personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addCat(@PathVariable UUID id, @RequestBody CatDto catDto) {
//        kafkaService.sendAsynchronously(CatRequestsMapper.addCat(catDto), "cat-topic");
        Cat cat = CatDtoMapper.mapToModel(catDto);
        personService.addCat(cat, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id, @RequestBody PersonDto personDto) {
        Person person = PersonDtoMapper.mapToModel(personDto);
        personService.updatePerson(id, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
