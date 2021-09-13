package com.example.springboot.controllers;

import com.example.springboot.dto.ChannelDto;
import com.example.springboot.dto.CommentDto;
import com.example.springboot.dto.VideoDto;
import com.example.springboot.models.Channel;
import com.example.springboot.models.Comment;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    private final VideoRepository repository;
    private final ChannelRepository chanrepository;
    private final StorageService service;
    @Autowired
    public VideoController(VideoRepository repository, StorageService service,ChannelRepository chanrepository) {
        this.repository = repository;
        this.service = service;
        this.chanrepository = chanrepository;
    }
    @GetMapping("/search/{search}")
    public List<VideoDto> searchVideos(@PathVariable String search) {
        List<VideoDto> res=new ArrayList<>();
        for (Video video:
                repository.findAll()) {
            if(video.getTitle().contains(search)||video.getDescription().contains(search)){
                ChannelDto chan=new ChannelDto(video.getChannel().getId(),video.getChannel().getHeader_src(),video.getChannel().getName(), (int) video.getChannel().getUser().getId());
                res.add(new VideoDto(video.getId(),video.getSrc(),video.getTitle(),video.getDescription(),video.getPreview(),chan));
            }
        }
        return res;
    }
    @GetMapping("/get/{id}")
    public VideoDto getById(@PathVariable int id) {
        Video vid=repository.findById(id).get();
        ChannelDto chan=new ChannelDto(vid.getChannel().getId(),vid.getChannel().getHeader_src(),vid.getChannel().getName(), (int) vid.getChannel().getUser().getId());
        return new VideoDto(vid.getId(),vid.getSrc(),vid.getTitle(),vid.getDescription(),vid.getPreview(),chan);
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
