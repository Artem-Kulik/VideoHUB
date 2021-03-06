package com.example.springboot.controllers;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.storage.StorageService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

//import static jdk.jpackage.internal.IOUtils.*;

@RestController
@RequestMapping("/user")
public class UserController  {
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
        return repository.findById((long) id).get();
    }
    @PostMapping("/add")
    public String add(@RequestBody User user) {
        repository.save(user);
        return "Success!";
    }
    @GetMapping(value = "/image/{id}")
    public @ResponseBody
    URL getImage(@PathVariable int id) throws IOException {
        User user = repository.findById((long) id).get();
        Resource file = service.loadAsResource(user.getIcon());
        return file.getURL();
    }

    @PostMapping("/add-icon/{id}")
    public String icon(@PathVariable int id,@RequestParam("file") MultipartFile file){
        String filename= UUID.randomUUID().toString();
        service.store(file,filename);
        User user=repository.findById((long) id).get();
        user.setIcon(file.getOriginalFilename());
        repository.save(user);
        return file.getOriginalFilename();
    }
    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = service.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "filename=\"" + file.getFilename() + "\"").body(file);

    }
    @GetMapping("/files-id/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable int id) {
        Resource file = service.loadAsResource(repository.findById((long) id).get().getIcon());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "filename=\"" + file.getFilename() + "\"").body(file);

    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,@RequestBody User user) {
        user.setId(id);
        repository.save(user);
        return "Success!";
    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        repository.delete(repository.findById((long) id).get());
        return "Success!";
    }
}
