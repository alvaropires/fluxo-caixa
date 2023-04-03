package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.dto.request.PersonRequest;
import com.construlider.fluxocaixa.dto.response.PersonResponse;
import com.construlider.fluxocaixa.models.Person;
import com.construlider.fluxocaixa.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Tag(name = "Person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/")
    @Operation(description = "Find All Persons.")
    public ResponseEntity<Page<PersonResponse>>findAll(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        Page<Person> personPage = personService.findAll(pageable);
        Page<PersonResponse> personResponsePage = personService.toPersonResponsePage(personPage);
        return new ResponseEntity<>(personResponsePage, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(description = "Find A Person By Id.")
    public ResponseEntity<PersonResponse> findById(@PathVariable int id){
        Person person = personService.findById(id);
        PersonResponse personResponse = personService.toPersonResponse(person);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(description = "Create A new Person.")
    public ResponseEntity<PersonResponse> create(@RequestBody PersonRequest personRequest){
        Person person = personService.toPerson(personRequest);
        personService.create(person);
        PersonResponse personResponse = personService.toPersonResponse(person);
        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update A Person By Id.")
    public ResponseEntity<PersonResponse> update(@PathVariable int id, @RequestBody PersonRequest personRequest){
        Person person = personService.update(id, personService.toPerson(personRequest));
        PersonResponse personResponse = personService.toPersonResponse(person);
        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete A Person By Id.")
    public ResponseEntity delete(@PathVariable int id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
