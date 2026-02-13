package com.estudosjava.curso.services;

import com.estudosjava.curso.dto.CategoryRequestDTO;
import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.repositories.CategoryRepository;
import com.estudosjava.curso.services.exceptions.DatabaseException;
import com.estudosjava.curso.services.exceptions.DuplicateResourceException;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ResourceNotFoundException(id));
    }

    public Category insert(CategoryRequestDTO dto){
        if (repository.existsByName(dto.getName())){
            throw new DuplicateResourceException("Category already exists");
        }

        Category category = new Category();

        category.setName(dto.getName());

        return repository.save(category);
    }

    public Category update(Long id, CategoryRequestDTO dto){
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if (!category.getName().equals(dto.getName()) && repository.existsByName(dto.getName())){
            throw new DuplicateResourceException("Category already exists");
        }

        category.setName(dto.getName());

        return repository.save(category);

    }

    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Category cannot be deleted because there are products linked to it");
        }
    }
}
