package com.estudosjava.curso.services;

import com.estudosjava.curso.dto.ProductDTO;
import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.entities.Product;
import com.estudosjava.curso.repositories.CategoryRepository;
import com.estudosjava.curso.repositories.ProductRepository;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
