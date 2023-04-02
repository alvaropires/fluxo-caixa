package com.construlider.fluxocaixa.exceptions;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(int id) {
        super("Person not found with id: " + id + ".");
    }
}
