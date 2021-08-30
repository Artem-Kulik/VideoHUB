package com.example.springboot.controllers;

import com.example.springboot.models.Channel;
import com.example.springboot.models.Video;
import com.example.springboot.repositories.ChannelRepository;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.repositories.VideoRepository;
import com.example.springboot.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    private final VideoRepository repository;
    private final StorageService service;
    @Autowired
    public VideoController(VideoRepository repository, StorageService service) {
        this.repository = repository;
        this.service = service;
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
    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = service.loadAsResource(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                "filename=\"" + file.getFilename() + "\"").body(file);

    }
}
