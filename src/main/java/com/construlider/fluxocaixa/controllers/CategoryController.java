package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.dto.request.CategoryRequest;
import com.construlider.fluxocaixa.dto.response.CategoryResponse;
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
    public ResponseEntity<List<CategoryResponse>> findAll(){
        List<Category> categories = categoryService.findAll();
        List<CategoryResponse> categoryResponseList = categoryService.toCategoryResponseList(categories);
        return new ResponseEntity<>(categoryResponseList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.toCategory(categoryRequest);
        categoryService.create(category);
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable int id){
        Category category = categoryService.findById(id);
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable int id, @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.update(id, categoryService.toCategory(categoryRequest));
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
