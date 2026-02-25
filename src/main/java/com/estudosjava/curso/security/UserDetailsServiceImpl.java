package com.estudosjava.curso.security;

import com.estudosjava.curso.entities.User;
import com.estudosjava.curso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(">>> Tentando carregar usuário por email: " + email);
        User user = repository.findByEmail(email);
        System.out.println(">>> Usuário encontrado no banco? " + (user != null ? "SIM - " + user.getEmail() : "NÃO"));

        if (user == null) {
            System.out.println(">>> Lançando UsernameNotFoundException para email: " + email);
            throw new UsernameNotFoundException("User not found: " + email);
        }

        return new UserDetailsImpl(user);
    }
}
