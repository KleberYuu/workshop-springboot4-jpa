package com.estudosjava.curso.resources;


import com.estudosjava.curso.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Kleber", "k@gmail.com", "990998575", "12345");
        return ResponseEntity.ok().body(u);
    }
}
