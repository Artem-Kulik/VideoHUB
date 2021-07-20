package com.example.springboot.controllers;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;
    private final StorageService service;
    @Autowired
    public UserController(UserRepository repository,StorageService service) {
        this.repository = repository;
        this.service = service;
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
    @PostMapping("/add-icon/{id}")
    public String icon(@PathVariable int id,@RequestParam("file") MultipartFile file){
        service.store(file);
        User user=repository.findById(id).get();
        user.setIcon(file.getOriginalFilename());
        repository.save(user);
        return file.getOriginalFilename();
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody User user) {
        user.setId(id);
        repository.save(user);
        return "Success!";
    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        repository.delete(repository.findById(id).get());
        return "Success!";
    }
}
