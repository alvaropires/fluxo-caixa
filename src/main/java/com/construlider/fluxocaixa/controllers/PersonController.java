package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.dto.request.PersonRequest;
import com.construlider.fluxocaixa.dto.response.PersonResponse;
import com.construlider.fluxocaixa.models.Person;
import com.construlider.fluxocaixa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public ResponseEntity<List<PersonResponse>>findAll(){
        List<Person> personList = personService.findAll();
        List<PersonResponse> personResponseList = personService.toPersonResponseList(personList);
        return new ResponseEntity<>(personResponseList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable int id){
        Person person = personService.findById(id);
        PersonResponse personResponse = personService.toPersonResponse(person);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PersonResponse> create(@RequestBody PersonRequest personRequest){
        Person person = personService.toPerson(personRequest);
        personService.create(person);
        PersonResponse personResponse = personService.toPersonResponse(person);
        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable int id, @RequestBody PersonRequest personRequest){
        Person person = personService.update(id, personService.toPerson(personRequest));
        PersonResponse personResponse = personService.toPersonResponse(person);
        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
