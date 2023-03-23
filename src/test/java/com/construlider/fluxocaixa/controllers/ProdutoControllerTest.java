package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Produto;
import com.construlider.fluxocaixa.service.ProdutoService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProdutoControllerTest {

    public static final String NOME = "maquina";
    private static final int ID = 1;
    private static final int INDEX = 0;

    private Produto produto = new Produto();
    @InjectMocks
    private ProdutoController produtoController;
    @Mock
    private ProdutoService produtoService;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startProduto();
    }

    @Test
    void quandoBuscaProdutoPorIdRetornaSucesso() {
        Mockito.when(produtoService.findById(Mockito.anyInt())).thenReturn(produto);
        ResponseEntity<Produto> response = produtoController.buscaProdutoPorId(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());

        System.out.println(response.getBody());

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(Produto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());


    }

    @Test
    void quandoBuscaProdutosRetornaUmaListaDeProduto() {
        Mockito.when(produtoService.findAll()).thenReturn(List.of(produto));
        ResponseEntity<List<Produto>> response = produtoController.buscaProdutos();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
        Assertions.assertEquals(Produto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(0).getId());
        assertEquals(NOME, response.getBody().get(0).getNome());
    }

    @Test
    void quandoCriaProdutoRetornaCriado() {
        Mockito.when(produtoService.create(Mockito.any())).thenReturn(produto);

        ResponseEntity<Produto> response = produtoController.criaProduto(produto);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void quandoAtualizaProdutoRetornaSucesso() {
        Mockito.when(produtoService.update(ID, produto)).thenReturn(produto);

        ResponseEntity<Produto> response = produtoController.atualizaProduto(ID, produto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(Produto.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NOME, response.getBody().getNome());
    }

    @Test
    void quandoDeletaProdutoEntaoRetornaSucesso() {
        Mockito.doNothing().when(produtoService).deleteById(Mockito.anyInt());

        ResponseEntity response = produtoController.deletaProduto(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(produtoService, Mockito.times(1)).deleteById(Mockito.anyInt());

    }

    private void startProduto(){
        produto = new Produto();
        produto.setId(ID);
        produto.setNome(NOME);
    }
}