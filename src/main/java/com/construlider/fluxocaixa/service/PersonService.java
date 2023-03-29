package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.models.Person;
import com.construlider.fluxocaixa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person create(Person person){
        return personRepository.save(person);
    }
    public Person findById(int id){
        return personRepository.findById(id).orElseThrow();
    }

    public Person update(int id, Person personUpdated){
        var pessoa = this.findById(id);
        pessoa = personUpdated;
        return personRepository.save(pessoa);
    }
    public void delete(int id){
        personRepository.deleteById(id);
    }

}
