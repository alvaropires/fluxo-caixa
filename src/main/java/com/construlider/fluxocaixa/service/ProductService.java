package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.dto.request.ProductRequest;
import com.construlider.fluxocaixa.dto.response.ProductResponse;
import com.construlider.fluxocaixa.exceptions.ProductNotFoundException;
import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Product create(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Product findById(int id){
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
    }
    public Product update(int id, Product updatedProduct){
        Product product = this.findById(id);
        product.setName(updatedProduct.getName());
        return productRepository.save(product);
    }
    public void deleteById(int id){
        productRepository.deleteById(id);
    }
    public ProductResponse toProductResponse(Product product){
        return modelMapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse> toProductResponseList(List<Product> products){
        return products.stream().map(this::toProductResponse).collect(Collectors.toList());
    }
    public Product toProduct(ProductRequest productRequest){
        return modelMapper.map(productRequest, Product.class);
    }
    public Page<ProductResponse> toProductResponsePage(Page<Product> productPage){
        return productPage.map(this::toProductResponse);
    }

}
