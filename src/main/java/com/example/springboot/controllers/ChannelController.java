package com.example.springboot.controllers;

import com.example.springboot.dto.ChannelDto;
import com.example.springboot.dto.VideoDto;
import com.example.springboot.models.Channel;
import com.example.springboot.models.User;
import com.example.springboot.models.Video;
import com.example.springboot.repositories.ChannelRepository;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    private final ChannelRepository repository;
    private final UserRepository userRepository;
    @Autowired
    public ChannelController(ChannelRepository repository,UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping("/getall")
    public List<Channel> index() {
        return (List<Channel>) repository.findAll();
    }
    @GetMapping("/get/{id}")
    public ChannelDto getById(@PathVariable int id) {
        Channel channel=repository.findById(id).get();
        return new ChannelDto(channel.getId(), channel.getHeader_src(), channel.getName(), (int) channel.getUser().getId());
    }
    @GetMapping("/get-user/{id}")
    public ChannelDto getByUserId(@PathVariable int id) {
        User user=userRepository.findById((long) id).get();
        Channel channel=repository.findByUser(user);
        return new ChannelDto(channel.getId(), channel.getHeader_src(), channel.getName(), (int) channel.getUser().getId());
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
