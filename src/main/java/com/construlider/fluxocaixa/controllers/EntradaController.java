package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Entrada;
import com.construlider.fluxocaixa.service.CategoriaService;
import com.construlider.fluxocaixa.service.DespesaService;
import com.construlider.fluxocaixa.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    private DespesaService despesaService;
    private CategoriaService categoriaService;
    private ProdutoService produtoService;

    public DespesaController(DespesaService despesaService, CategoriaService categoriaService, ProdutoService produtoService) {
        this.despesaService = despesaService;
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Entrada>> buscaDespesas(){
        var despesas = despesaService.findAll();
        return new ResponseEntity<List<Entrada>>(despesas, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Entrada> criaDespesa(@RequestBody Entrada entrada){
        var despesaCriada = despesaService.create(entrada);
        return new ResponseEntity<>(despesaCriada, HttpStatus.CREATED);
    }

    @GetMapping("categoria/{id}")
    public ResponseEntity<List<Entrada>> buscaDespesasDaCategoria(@PathVariable int id){
        var categoria = categoriaService.findById(id);
        return new ResponseEntity<>(despesaService.getDespesasDaCategoria(categoria), HttpStatus.OK);
    }
    @GetMapping("produto/{id}")
    public ResponseEntity<List<Entrada>> buscaDespesasDoProduto(@PathVariable int id){
        var produto = produtoService.findById(id);
        return new ResponseEntity<>(despesaService.getDespesasDoProduto(produto), HttpStatus.OK);
    }
}
