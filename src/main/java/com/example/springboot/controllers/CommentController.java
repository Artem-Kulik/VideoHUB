package com.example.springboot.controllers;

import com.example.springboot.models.Comment;
import com.example.springboot.models.Like;
import com.example.springboot.repositories.CommentsRepository;
import com.example.springboot.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentsRepository repository;
    @Autowired
    public CommentController(CommentsRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/getall")
    public List<Comment> index() {
        return (List<Comment>) repository.findAll();
    }
    @GetMapping("/get/{id}")
    public Comment getById(@PathVariable int id) {
        return repository.findById(id).get();
    }
    //@GetMapping("/getname/{id}")
    //public Channel getByName(@PathVariable String name) {
    //    return repository.find(id).get();
    //}
    @PostMapping("/add")
    public String add(@RequestBody Comment comment) {
        repository.save(comment);
        return "Success!";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody Comment comment) {
        comment.setId(id);
        repository.save(comment);
        return "Success!";
    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        repository.delete(repository.findById(id).get());
        return "Success!";
    }
}
