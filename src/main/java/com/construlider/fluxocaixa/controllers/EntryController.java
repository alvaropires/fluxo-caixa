package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.dto.request.EntryRequest;
import com.construlider.fluxocaixa.dto.response.EntryResponse;
import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.models.Entry;
import com.construlider.fluxocaixa.models.Person;
import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.models.enums.TypeEntry;
import com.construlider.fluxocaixa.service.CategoryService;
import com.construlider.fluxocaixa.service.EntryService;
import com.construlider.fluxocaixa.service.PersonService;
import com.construlider.fluxocaixa.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
@Tag(name = "Entry")
public class EntryController {

    private EntryService entryService;
    private CategoryService categoryService;
    private ProductService productService;
    private PersonService personService;

    public EntryController(EntryService entryService, CategoryService categoryService, ProductService productService, PersonService personService) {
        this.entryService = entryService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.personService = personService;
    }

    @GetMapping("/")
    @Operation(description = "Find All Entries.")
    public ResponseEntity<List<EntryResponse>> findAll(){
        List<Entry> entries = entryService.findAll();
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(description = "Create A New Entry.")
    public ResponseEntity<EntryResponse> create(@RequestBody EntryRequest entryRequest){
        Entry entry = entryService.toEntry(entryRequest);
        entryService.create(entry);
        EntryResponse entryResponse = entryService.toEntryResponse(entry);
        return new ResponseEntity<>(entryResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(description = "Find A Entry By Id.")
    public ResponseEntity<EntryResponse> findById(@PathVariable int id){
        Entry entry = entryService.findById(id);
        EntryResponse entryResponse = entryService.toEntryResponse(entry);
        return new ResponseEntity<>(entryResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update A Entry By Id.")
    public ResponseEntity<EntryResponse> update(@PathVariable int id, @RequestBody EntryRequest entryRequest){
        Entry entry = entryService.update(id, entryService.toEntry(entryRequest));
        EntryResponse entryResponse = entryService.toEntryResponse(entry);
        return new ResponseEntity<>(entryResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete A Entry By Id.")
    public ResponseEntity delete(@PathVariable int id){
        entryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("category/{id}")
    @Operation(description = "Find Entries By Category.")
    public ResponseEntity<List<EntryResponse>> findEntriesFromCategory(@PathVariable int id){
        Category category = categoryService.findById(id);
        List<Entry> entries = entryService.getEntriesFromCategory(category);
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }
    @GetMapping("product/{id}")
    @Operation(description = "Find Entries By Product.")
    public ResponseEntity<List<EntryResponse>> findEntriesFromProduct(@PathVariable int id){
        Product product = productService.findById(id);
        List<Entry> entries = entryService.getEntriesFromProduct(product);
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }

    @GetMapping("type-entry/{typeEntry}")
    @Operation(description = "Find Entries By Type of Entry (EXPENSE/INCOME).")
    public ResponseEntity<List<EntryResponse>> getEntriesFromTypeEntry(@PathVariable TypeEntry typeEntry){
        List<Entry> entries = entryService.getEntriesFromTypeEntry(typeEntry);
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }

    @GetMapping("person/{id}")
    @Operation(description = "Find Entries By Person.")
    public ResponseEntity<List<EntryResponse>> getEntriesFromPerson(@PathVariable int id){
        Person person = personService.findById(id);
        List<Entry> entries = entryService.getEntriesFromPerson(person);
        List<EntryResponse> entryResponseList = entryService.toEntryResponseList(entries);
        return new ResponseEntity<>(entryResponseList, HttpStatus.OK);
    }
}
