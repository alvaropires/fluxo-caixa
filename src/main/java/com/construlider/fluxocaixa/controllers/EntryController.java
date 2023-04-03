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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @Autowired
    public EntryController(EntryService entryService, CategoryService categoryService, ProductService productService, PersonService personService) {
        this.entryService = entryService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.personService = personService;
    }

    @GetMapping("/")
    @Operation(description = "Find All Entries.")
    public ResponseEntity<Page<EntryResponse>> findAll(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable,
                                                       @RequestParam(required = false) String flag){
        Page<Entry> entryPage = entryService.findAll(pageable, flag);
        Page<EntryResponse> entryResponsePage = entryService.toEntryResponsePage(entryPage);
        return new ResponseEntity<>(entryResponsePage, HttpStatus.OK);
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
    public ResponseEntity<Page<EntryResponse>> findEntriesFromCategory(@PathVariable int id, @PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        Category category = categoryService.findById(id);
        Page<Entry> entryPage = entryService.getEntriesFromCategory(category, pageable);
        Page<EntryResponse> entryResponsePage = entryService.toEntryResponsePage(entryPage);
        return new ResponseEntity<>(entryResponsePage, HttpStatus.OK);
    }
    @GetMapping("product/{id}")
    @Operation(description = "Find Entries By Product.")
    public ResponseEntity<Page<EntryResponse>> findEntriesFromProduct(@PathVariable int id, @PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        Product product = productService.findById(id);
        Page<Entry> entryPage = entryService.getEntriesFromProduct(product, pageable);
        Page<EntryResponse> entryResponsePage = entryService.toEntryResponsePage(entryPage);
        return new ResponseEntity<>(entryResponsePage, HttpStatus.OK);
    }

    @GetMapping("person/{id}")
    @Operation(description = "Find Entries By Person.")
    public ResponseEntity<Page<EntryResponse>> getEntriesFromPerson(@PathVariable int id, @PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        Person person = personService.findById(id);
        Page<Entry> entryPage = entryService.getEntriesFromPerson(person, pageable);
        Page<EntryResponse> entryResponsePage = entryService.toEntryResponsePage(entryPage);
        return new ResponseEntity<>(entryResponsePage, HttpStatus.OK);
    }
}
