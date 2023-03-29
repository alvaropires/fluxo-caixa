package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(int id){
        return productRepository.findById(id).orElseThrow();
    }
    public Product update(int id, Product updatedProduct){
        Product product = this.findById(id);
        product.setName(updatedProduct.getName());
        return productRepository.save(product);
    }
    public void deleteById(int id){
        productRepository.deleteById(id);
    }

}
