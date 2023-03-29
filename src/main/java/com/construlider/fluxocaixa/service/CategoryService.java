package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

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




}
