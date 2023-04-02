package com.construlider.fluxocaixa.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(int id) {
        super("Category not found with id: " + id + ".");
    }
}
