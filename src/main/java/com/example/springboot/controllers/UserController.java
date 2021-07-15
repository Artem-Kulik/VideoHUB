package com.example.springboot.controllers;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;
    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/users")
    public List<User> index() {
        return (List<User>) repository.findAll();
    }
    @GetMapping("/get/{id}")
    public User getById(@PathVariable int id) {
        return repository.findById(id).get();
    }
    @PostMapping("/add")
    public String add(@RequestBody User user) {
        repository.save(user);
        return "Success!";
    }
}
