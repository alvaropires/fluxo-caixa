package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Entry;
import com.construlider.fluxocaixa.models.enums.TypeEntry;
import com.construlider.fluxocaixa.service.CategoryService;
import com.construlider.fluxocaixa.service.EntryService;
import com.construlider.fluxocaixa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {

    private EntryService entryService;
    private CategoryService categoryService;
    private ProductService productService;

    public EntryController(EntryService entryService, CategoryService categoryService, ProductService productService) {
        this.entryService = entryService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Entry>> findAll(){
        var entries = entryService.findAll();
        return new ResponseEntity<List<Entry>>(entries, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Entry> create(@RequestBody Entry entry){
        var createdEntry = entryService.create(entry);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> findById(@PathVariable int id){
        var entry = entryService.findById(id);
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> update(@PathVariable int id, @RequestBody Entry updatedEntry){
        var entry = entryService.update(id, updatedEntry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        entryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<Entry>> findEntriesFromCategory(@PathVariable int id){
        var categories = categoryService.findById(id);
        return new ResponseEntity<>(entryService.getEntriesFromCategory(categories), HttpStatus.OK);
    }
    @GetMapping("product/{id}")
    public ResponseEntity<List<Entry>> findEntriesFromProduct(@PathVariable int id){
        var products = productService.findById(id);
        return new ResponseEntity<>(entryService.getEntriesFromProduct(products), HttpStatus.OK);
    }

    @GetMapping("type-entry/{typeEntry}")
    public ResponseEntity<List<Entry>> getEntriesFromTypeEntry(@PathVariable TypeEntry typeEntry){
        var entries = entryService.getEntriesFromTypeEntry(typeEntry);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }
}
