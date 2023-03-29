package com.construlider.fluxocaixa.repository;

import com.construlider.fluxocaixa.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
