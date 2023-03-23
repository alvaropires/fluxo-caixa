package com.construlider.fluxocaixa.controllers;

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

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaProdutoPorId(@PathVariable int id){
        var produto = produtoService.findById(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
//        return ResponseEntity.ok().body(produto);
    }
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

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizaProduto(@PathVariable int id, @RequestBody Produto novoProduto){
        var produto = produtoService.update(id, novoProduto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaProduto(@PathVariable int id){
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
