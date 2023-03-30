package com.construlider.fluxocaixa.controllers;

import com.construlider.fluxocaixa.dto.request.CategoryRequest;
import com.construlider.fluxocaixa.dto.response.CategoryResponse;
import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @Operation(description = "Find All Categories.")
    public ResponseEntity<List<CategoryResponse>> findAll(){
        List<Category> categories = categoryService.findAll();
        List<CategoryResponse> categoryResponseList = categoryService.toCategoryResponseList(categories);
        return new ResponseEntity<>(categoryResponseList, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(description = "Create A New Category.")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.toCategory(categoryRequest);
        categoryService.create(category);
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(description = "Find A Category By Id.")
    public ResponseEntity<CategoryResponse> findById(@PathVariable int id){
        Category category = categoryService.findById(id);
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update A Category By Id.")
    public ResponseEntity<CategoryResponse> update(@PathVariable int id, @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.update(id, categoryService.toCategory(categoryRequest));
        CategoryResponse categoryResponse = categoryService.toCategoryResponse(category);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete A Category By Id.")
    public ResponseEntity delete(@PathVariable int id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
