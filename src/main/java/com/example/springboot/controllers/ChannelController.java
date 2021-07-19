package com.example.springboot.controllers;

import com.example.springboot.models.Channel;
import com.example.springboot.models.User;
import com.example.springboot.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    private final ChannelRepository repository;
    @Autowired
    public ChannelController(ChannelRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/channels")
    public List<Channel> index() {
        return (List<Channel>) repository.findAll();
    }
    @GetMapping("/get/{id}")
    public Channel getById(@PathVariable int id) {
        return repository.findById(id).get();
    }
    //@GetMapping("/getname/{id}")
    //public Channel getByName(@PathVariable String name) {
    //    return repository.find(id).get();
    //}
    @PostMapping("/add")
    public String add(@RequestBody Channel channel) {
        repository.save(channel);
        return "Success!";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody Channel channel) {
        channel.setId(id);
        repository.save(channel);
        return "Success!";
    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        repository.delete(repository.findById(id).get());
        return "Success!";
    }
}
