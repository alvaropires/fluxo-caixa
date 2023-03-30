package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.dto.request.EntryRequest;
import com.construlider.fluxocaixa.dto.response.EntryResponse;
import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.models.Entry;
import com.construlider.fluxocaixa.models.Product;
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
    public ResponseEntity<List<EntryResponse>> findAll(){
        List<Entry> entries = entryService.findAll();
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EntryResponse> create(@RequestBody EntryRequest entryRequest){
        Entry entry = entryService.toEntry(entryRequest);
        entryService.create(entry);
        EntryResponse entryResponse = entryService.toEntryResponse(entry);
        return new ResponseEntity<>(entryResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryResponse> findById(@PathVariable int id){
        Entry entry = entryService.findById(id);
        EntryResponse entryResponse = entryService.toEntryResponse(entry);
        return new ResponseEntity<>(entryResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntryResponse> update(@PathVariable int id, @RequestBody EntryRequest entryRequest){
        Entry entry = entryService.update(id, entryService.toEntry(entryRequest));
        EntryResponse entryResponse = entryService.toEntryResponse(entry);
        return new ResponseEntity<>(entryResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        entryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<EntryResponse>> findEntriesFromCategory(@PathVariable int id){
        Category category = categoryService.findById(id);
        List<Entry> entries = entryService.getEntriesFromCategory(category);
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }
    @GetMapping("product/{id}")
    public ResponseEntity<List<EntryResponse>> findEntriesFromProduct(@PathVariable int id){
        Product product = productService.findById(id);
        List<Entry> entries = entryService.getEntriesFromProduct(product);
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }

    @GetMapping("type-entry/{typeEntry}")
    public ResponseEntity<List<EntryResponse>> getEntriesFromTypeEntry(@PathVariable TypeEntry typeEntry){
        List<Entry> entries = entryService.getEntriesFromTypeEntry(typeEntry);
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }
}
