package com.construlider.fluxocaixa.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(int id) {
        super("Product not found with id: " + id + ".");
    }
}
