package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Despesa;
import com.construlider.fluxocaixa.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {
    @Autowired
    private DespesaService despesaService;

    @GetMapping("/")
    public ResponseEntity<List<Despesa>> buscaDespesas(){
        var despesas = despesaService.findAll();
        return new ResponseEntity<List<Despesa>>(despesas, HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<Despesa> criaDespesa(@RequestBody Despesa despesa){
        var despesaCriada = despesaService.create(despesa);
        return new ResponseEntity<>(despesaCriada, HttpStatus.CREATED);
    }
}
