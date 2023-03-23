package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Entrada;
import com.construlider.fluxocaixa.models.enums.TipoEntrada;
import com.construlider.fluxocaixa.service.CategoriaService;
import com.construlider.fluxocaixa.service.EntradaService;
import com.construlider.fluxocaixa.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrada")
public class EntradaController {

    private EntradaService entradaService;
    private CategoriaService categoriaService;
    private ProdutoService produtoService;

    public EntradaController(EntradaService entradaService, CategoriaService categoriaService, ProdutoService produtoService) {
        this.entradaService = entradaService;
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Entrada>> buscaEntradas(){
        var entradas = entradaService.findAll();
        return new ResponseEntity<List<Entrada>>(entradas, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Entrada> criaEntrada(@RequestBody Entrada entrada){
        var entradaCriada = entradaService.create(entrada);
        return new ResponseEntity<>(entradaCriada, HttpStatus.CREATED);
    }

    @GetMapping("categoria/{id}")
    public ResponseEntity<List<Entrada>> buscaEntradasDaCategoria(@PathVariable int id){
        var categoria = categoriaService.findById(id);
        return new ResponseEntity<>(entradaService.getEntradasDaCategoria(categoria), HttpStatus.OK);
    }
    @GetMapping("produto/{id}")
    public ResponseEntity<List<Entrada>> buscaEntradasDoProduto(@PathVariable int id){
        var produto = produtoService.findById(id);
        return new ResponseEntity<>(entradaService.getEntradasDoProduto(produto), HttpStatus.OK);
    }

    @GetMapping("tipo-de-entrada/{tipoEntrada}")
    public ResponseEntity<List<Entrada>> buscaEntradasPorTipoDeEntrada(@PathVariable TipoEntrada tipoEntrada){
        var entradas = entradaService.getEntradasPorTipoDeEntrada(tipoEntrada);
        return new ResponseEntity<>(entradas, HttpStatus.OK);
    }
}
