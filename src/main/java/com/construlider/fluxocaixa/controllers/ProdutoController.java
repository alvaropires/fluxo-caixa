package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Despesa;
import com.construlider.fluxocaixa.models.Produto;
import com.construlider.fluxocaixa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @GetMapping("/")
    public ResponseEntity<List<Produto>> buscaProdutos(){
        var produtos = produtoService.findAll();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Produto> criaProduto(@RequestBody Produto produto){
        var produtoNovo = produtoService.create(produto);
        return new ResponseEntity<>(produtoNovo, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Despesa>> buscaProdutosDaCategoria(@PathVariable int id){
        var produto = produtoService.findById(id);
        System.out.println(produto);
        return new ResponseEntity<>(produtoService.getDespesasDoProduto(produto), HttpStatus.OK);
    }
}
