package com.example.springboot.controllers;

import com.example.springboot.models.Channel;
import com.example.springboot.models.Video;
import com.example.springboot.repositories.ChannelRepository;
import com.example.springboot.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    private final VideoRepository repository;
    @Autowired
    public VideoController(VideoRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/getall")
    public List<Video> index() {
        return (List<Video>) repository.findAll();
    }
    @GetMapping("/get/{id}")
    public Video getById(@PathVariable int id) {
        return repository.findById(id).get();
    }
    //@GetMapping("/getname/{id}")
    //public Channel getByName(@PathVariable String name) {
    //    return repository.find(id).get();
    //}
    @PostMapping("/add")
    public String add(@RequestBody Video video) {
        repository.save(video);
        return "Success!";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody Video video) {
        video.setId(id);
        repository.save(video);
        return "Success!";
    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        repository.delete(repository.findById(id).get());
        return "Success!";
    }
}
