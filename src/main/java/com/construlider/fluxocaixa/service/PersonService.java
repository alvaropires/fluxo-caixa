package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.dto.request.PersonRequest;
import com.construlider.fluxocaixa.dto.response.PersonResponse;
import com.construlider.fluxocaixa.exceptions.PersonNotFoundException;
import com.construlider.fluxocaixa.models.Person;
import com.construlider.fluxocaixa.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }


    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Page<Person> findAll(Pageable pageable){
        return personRepository.findAll(pageable);
    }
    public Person create(Person person){
        return personRepository.save(person);
    }
    public Person findById(int id){
        return personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));
    }

    public Person update(int id, Person personUpdated){
        var person = this.findById(id);
        person.setName(personUpdated.getName());
        person.setPhoneNumber(personUpdated.getPhoneNumber());
        person.setEmail(personUpdated.getEmail());
        person.setAdress(personUpdated.getAdress());
        person.setTypePerson(personUpdated.getTypePerson());
        return personRepository.save(person);
    }
    public void delete(int id){
        personRepository.deleteById(id);
    }

    public PersonResponse toPersonResponse(Person person){
        return modelMapper.map(person, PersonResponse.class);
    }

    public List<PersonResponse> toPersonResponseList(List<Person> personList){
        return personList.stream().map(this::toPersonResponse).collect(Collectors.toList());
    }

    public Person toPerson(PersonRequest personRequest){
        return modelMapper.map(personRequest, Person.class);
    }
    public Page<PersonResponse> toPersonResponsePage(Page<Person> personPage){
        return personPage.map(this::toPersonResponse);
    }

}
