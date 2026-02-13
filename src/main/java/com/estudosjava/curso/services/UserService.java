package com.estudosjava.curso.services;

import com.estudosjava.curso.dto.UserRequestDTO;
import com.estudosjava.curso.entities.User;
import com.estudosjava.curso.repositories.UserRepository;
import com.estudosjava.curso.services.exceptions.BusinessException;
import com.estudosjava.curso.services.exceptions.DatabaseException;
import com.estudosjava.curso.services.exceptions.DuplicateResourceException;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow( () -> new ResourceNotFoundException(id));
    }

    public User insert(UserRequestDTO dto){
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email already in use");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        return repository.save(user);
    }

    public User update(Long id, UserRequestDTO dto){

        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if (!user.getEmail().equals(dto.getEmail())
                && repository.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException("Email already registered");
        }

        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());

        return repository.save(user);
    }

    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("User cannot be deleted because there are orders linked to it");
        }
    }
}
