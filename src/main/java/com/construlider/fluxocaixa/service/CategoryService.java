package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.dto.request.CategoryRequest;
import com.construlider.fluxocaixa.dto.response.CategoryResponse;
import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public Category create(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(int id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category update(int id, Category updatedCategory){
        var category = this.findById(id);
        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }

    public void deleteById(int id){
        categoryRepository.deleteById(id);
    }

    public CategoryResponse toCategoryResponse(Category category){
        return modelMapper.map(category, CategoryResponse.class);
    }

    public List<CategoryResponse> toCategoryResponseList(List<Category> categories){
        return categories.stream().map(this::toCategoryResponse).collect(Collectors.toList());
    }

    public Category toCategory(CategoryRequest categoryRequest){
        return modelMapper.map(categoryRequest, Category.class);
    }




}
