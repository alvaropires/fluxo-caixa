package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductControllerTest {

    @Test
    void contextLoads() {
    }
//
//    public static final String NOME = "maquina";
//    private static final int ID = 1;
//    private static final int INDEX = 0;
//c
//    private Product product = new Product();
//    @InjectMocks
//    private ProductController productController;
//    @Mock
//    private ProductService productService;
//
//
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        startProduto();
//    }
//
//    @Test
//    void quandoBuscaProdutoPorIdRetornaSucesso() {
//        Mockito.when(productService.findById(Mockito.anyInt())).thenReturn(product);
//        ResponseEntity<Product> response = productController.findById(ID);
//
//        Assertions.assertNotNull(response);
//        Assertions.assertNotNull(response.getBody());
//
//        System.out.println(response.getBody());
//
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertEquals(Product.class, response.getBody().getClass());
//
//        assertEquals(ID, response.getBody().getId());
//        assertEquals(NOME, response.getBody().getName());
//
//
//    }
//
//    @Test
//    void quandoBuscaProdutosRetornaUmaListaDeProduto() {
//        Mockito.when(productService.findAll()).thenReturn(List.of(product));
//        ResponseEntity<List<Product>> response = productController.findAll();
//
//        Assertions.assertNotNull(response);
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
////        Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
//        Assertions.assertEquals(Product.class, response.getBody().get(INDEX).getClass());
//
//        assertEquals(ID, response.getBody().get(0).getId());
//        assertEquals(NOME, response.getBody().get(0).getName());
//    }
//
//    @Test
//    void quandoCriaProdutoRetornaCriado() {
//        Mockito.when(productService.create(Mockito.any())).thenReturn(product);
//
//        ResponseEntity<Product> response = productController.create(product);
//
//        assertEquals(ResponseEntity.class, response.getClass());
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    }
//
//    @Test
//    void quandoAtualizaProdutoRetornaSucesso() {
//        Mockito.when(productService.update(ID, product)).thenReturn(product);
//
//        ResponseEntity<Product> response = productController.update(ID, product);
//
//        Assertions.assertNotNull(response);
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertEquals(Product.class, response.getBody().getClass());
//
//        Assertions.assertEquals(ID, response.getBody().getId());
//        Assertions.assertEquals(NOME, response.getBody().getName());
//    }
//
//    @Test
//    void quandoDeletaProdutoEntaoRetornaSucesso() {
//        Mockito.doNothing().when(productService).deleteById(Mockito.anyInt());
//
//        ResponseEntity response = productController.delete(ID);
//
//        assertNotNull(response);
//        assertEquals(ResponseEntity.class, response.getClass());
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        Mockito.verify(productService, Mockito.times(1)).deleteById(Mockito.anyInt());
//
//    }
//
//    private void startProduto(){
//        product = new Product();
//        product.setId(ID);
//        product.setName(NOME);
//    }
}