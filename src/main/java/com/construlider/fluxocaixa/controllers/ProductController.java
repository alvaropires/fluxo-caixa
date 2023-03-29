package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable int id){
        var product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> findAll(){
        var products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> create(@RequestBody Product product){
        var createdProduct = productService.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product updatedProduct){
        var product = productService.update(id, updatedProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
