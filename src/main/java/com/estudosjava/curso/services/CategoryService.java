package com.estudosjava.curso.services;

import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.repositories.CategoryRepository;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
        return obj.get();
    }

    public Category insert(Category obj){
        return repository.save(obj);
    }

    public Category update(Long id, Category obj){
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(obj.getName());
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
