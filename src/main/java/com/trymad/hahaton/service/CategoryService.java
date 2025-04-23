package com.trymad.hahaton.service;

import com.trymad.hahaton.entity.Category;
import com.trymad.hahaton.entity.CategoryType;
import com.trymad.hahaton.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Category getById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Category getFetchById(Integer id) {
        return categoryRepository.findFetchById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Category getByName(CategoryType name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with name: " + name));
    }

    @Transactional(readOnly = true)
    public Category getFetchByName(CategoryType name) {
        return categoryRepository.findFetchByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with name: " + name));
    }
}
