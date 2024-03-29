package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.dto.request.CategoryRequest;
import com.construlider.fluxocaixa.dto.response.CategoryResponse;
import com.construlider.fluxocaixa.exceptions.CategoryNotFoundException;
import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<Category> findAll(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    public Category findById(int id){
        return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
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

    public Page<CategoryResponse> toCategoryResponsePage(Page<Category> categoryPage){
        return categoryPage.map(this::toCategoryResponse);
    }




}
