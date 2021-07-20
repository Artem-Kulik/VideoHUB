package com.example.springboot.controllers;

import com.example.springboot.models.Like;
import com.example.springboot.models.Video;
import com.example.springboot.repositories.LikeRepository;
import com.example.springboot.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeRepository repository;
    @Autowired
    public LikeController(LikeRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/getall")
    public List<Like> index() {
        return (List<Like>) repository.findAll();
    }
    @GetMapping("/get/{id}")
    public Like getById(@PathVariable int id) {
        return repository.findById(id).get();
    }
    //@GetMapping("/getname/{id}")
    //public Channel getByName(@PathVariable String name) {
    //    return repository.find(id).get();
    //}
    @PostMapping("/add")
    public String add(@RequestBody Like like) {
        repository.save(like);
        return "Success!";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody Like like) {
        like.setId(id);
        repository.save(like);
        return "Success!";
    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        repository.delete(repository.findById(id).get());
        return "Success!";
    }
}
