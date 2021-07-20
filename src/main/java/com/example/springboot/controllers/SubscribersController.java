package com.example.springboot.controllers;

import com.example.springboot.models.Comment;
import com.example.springboot.models.Subscriber;
import com.example.springboot.repositories.CommentsRepository;
import com.example.springboot.repositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriber")
public class SubscribersController {
    private final SubscriberRepository repository;
    @Autowired
    public SubscribersController(SubscriberRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/getall")
    public List<Subscriber> index() {
        return (List<Subscriber>) repository.findAll();
    }
    @GetMapping("/get/{id}")
    public Subscriber getById(@PathVariable int id) {
        return repository.findById(id).get();
    }
    //@GetMapping("/getname/{id}")
    //public Channel getByName(@PathVariable String name) {
    //    return repository.find(id).get();
    //}
    @PostMapping("/add")
    public String add(@RequestBody Subscriber subscriber) {
        repository.save(subscriber);
        return "Success!";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody Subscriber subscriber) {
        subscriber.setId(id);
        repository.save(subscriber);
        return "Success!";
    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        repository.delete(repository.findById(id).get());
        return "Success!";
    }
}
