package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.dto.request.ProductRequest;
import com.construlider.fluxocaixa.dto.response.ProductResponse;
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
    public ResponseEntity<ProductResponse> findById(@PathVariable int id){
        Product product = productService.findById(id);
        ProductResponse productResponse = productService.toProductResponse(product);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<Product> products = productService.findAll();
        List<ProductResponse> productResponseList = productService.toProductResponseList(products);
        return new ResponseEntity<>(productResponseList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest){
        Product product = productService.toProduct(productRequest);
        productService.create(product);
        ProductResponse productResponse = productService.toProductResponse(product);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable int id, @RequestBody ProductRequest productRequest){
        Product product = productService.update(id, productService.toProduct(productRequest));
        ProductResponse productResponse = productService.toProductResponse(product);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
