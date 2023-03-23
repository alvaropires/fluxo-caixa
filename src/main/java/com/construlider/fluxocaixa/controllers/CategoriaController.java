package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Categoria;
import com.construlider.fluxocaixa.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> buscaCategorias(){
        var categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Categoria> criaCategoria(@RequestBody Categoria categoria){
        var categoriaCriada = categoriaService.create(categoria);
        return new ResponseEntity<>(categoriaCriada, HttpStatus.CREATED);
    }

}
