package com.construlider.fluxocaixa.exceptions;

public class EntryNotFoundException extends RuntimeException{
    public EntryNotFoundException(int id) {
        super("Entry not found with id: " + id + ".");
    }
}
