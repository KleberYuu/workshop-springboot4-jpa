package com.estudosjava.curso.security;

import com.estudosjava.curso.entities.User;
import com.estudosjava.curso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = repository.findByEmail(email);

        if (user.isEmpty()) {
             throw new UsernameNotFoundException("User not found: " + email);
        }

        return new UserDetailsImpl(user.orElse(null));
    }
}
