package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> findAll(){
        var categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Category> create(@RequestBody Category category){
        var createdCategory = categoryService.create(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable int id){
        var category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable int id, @RequestBody Category updatedCategory){
        var category = categoryService.update(id, updatedCategory);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
