package com.estudosjava.curso.services;

import com.estudosjava.curso.dto.ProductDTO;
import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.entities.Product;
import com.estudosjava.curso.repositories.CategoryRepository;
import com.estudosjava.curso.repositories.ProductRepository;
import com.estudosjava.curso.services.exceptions.DatabaseException;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ResourceNotFoundException(id));
    }

    public Product insert(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());

        for (Long catId : dto.getCategoryIds()) {
            Category category = categoryRepository
                    .findById(catId)
                    .orElseThrow(() -> new ResourceNotFoundException(catId));

            product.getCategories().add(category);
        }

        return repository.save(product);
    }

    public Product update(Long id, ProductDTO dto){
        try {
            Product product = repository.getReferenceById(id);
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setImgUrl(dto.getImgUrl());

            product.getCategories().clear();
            for (Long catId : dto.getCategoryIds()) {
                Category category = categoryRepository
                        .findById(catId)
                        .orElseThrow(() -> new ResourceNotFoundException(catId));

                product.getCategories().add(category);
            }
            return repository.save(product);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
