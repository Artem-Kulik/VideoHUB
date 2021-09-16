package com.example.springboot.controllers;

import com.example.springboot.dto.ChannelDto;
import com.example.springboot.dto.CommentDto;
import com.example.springboot.dto.VideoDto;
import com.example.springboot.dto.VideoRequest;
import com.example.springboot.models.Channel;
import com.example.springboot.models.Comment;
import com.example.springboot.models.User;
import com.example.springboot.models.Video;
import com.example.springboot.repositories.ChannelRepository;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.repositories.VideoRepository;
import com.example.springboot.storage.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/getall")
    public List<VideoDto> index() {
        List<Video> rand=(List<Video>)repository.findAll();
        int random = new Random().nextInt();
        int size=rand.size();
        if(size>18){
            size=18;
        }
        rand=rand.stream().sorted(Comparator.comparingInt(o -> System.identityHashCode(o) ^ random)).collect(Collectors.toList()).subList(0,size);

        List<VideoDto> res=new ArrayList<>();
        for (Video video:
                rand) {
                ChannelDto chan=new ChannelDto(video.getChannel().getId(),video.getChannel().getHeader_src(),video.getChannel().getName(), (int) video.getChannel().getUser().getId());
                res.add(new VideoDto(video.getId(),video.getSrc(),video.getTitle(),video.getDescription(),video.getPreview(),chan,video.getCreatedAt()));
        }
        return res;
    }

//    @GetMapping("/get-page/{items}&{page}")
//    public List<VideoDto> searchVideos(@PathVariable int items,@PathVariable int page) {
//        List<VideoDto> res=new ArrayList<>();
//        final int pageIndex = page - 1 < 0 ? 0 : page - 1;
//
//        List<Video> rand=(List<Video>)repository.findAll();
//        int random = new Random().nextInt();
//        int size=rand.size();
//        if(size>5){
//            size=5;
//        }
//        rand=rand.stream().sorted(Comparator.comparingInt(o -> System.identityHashCode(o) ^ random)).collect(Collectors.toList()).subList(0,size);
//
//        List<Video> videos=new ArrayList<>();
//        for (int i=page*items;i<(page*items)+items;i++){
//            videos.add(rand.get(i));
//        }
//        for (Video video:
//                Arrays.copyOfRange(repository.findAllList(),page*items,(page*items)+items)) {
//                ChannelDto chan=new ChannelDto(video.getChannel().getId(),video.getChannel().getHeader_src(),video.getChannel().getName(), (int) video.getChannel().getUser().getId());
//                res.add(new VideoDto(video.getId(),video.getSrc(),video.getTitle(),video.getDescription(),video.getPreview(),chan));
//        }
//        return res;
//    }

    @GetMapping("/search/{search}")
    public List<VideoDto> searchVideos(@PathVariable String search) {
        List<VideoDto> res=new ArrayList<>();
        for (Video video:
                repository.findAll()) {
            if(video.getTitle().contains(search)||video.getDescription().contains(search)){
                ChannelDto chan=new ChannelDto(video.getChannel().getId(),video.getChannel().getHeader_src(),video.getChannel().getName(), (int) video.getChannel().getUser().getId());
                res.add(new VideoDto(video.getId(),video.getSrc(),video.getTitle(),video.getDescription(),video.getPreview(),chan,video.getCreatedAt()));
            }
        }
        return res;
    }
    @GetMapping("/user/{username}")
    public List<VideoDto> userVideos(@PathVariable String username) {
        List<VideoDto> res=new ArrayList<>();
        for (Video video:
                repository.findAll()) {
            String user=video.getChannel().getUser().getName();
            if(user.equalsIgnoreCase(username)){
                ChannelDto chan=new ChannelDto(video.getChannel().getId(),video.getChannel().getHeader_src(),video.getChannel().getName(), (int) video.getChannel().getUser().getId());
                res.add(new VideoDto(video.getId(),video.getSrc(),video.getTitle(),video.getDescription(),video.getPreview(),chan,video.getCreatedAt()));
            }
        }
        return res;
    }
    @GetMapping("/get/{id}")
    public VideoDto getById(@PathVariable int id) {
        Video vid=repository.findById(id).get();
        ChannelDto chan=new ChannelDto(vid.getChannel().getId(),vid.getChannel().getHeader_src(),vid.getChannel().getName(), (int) vid.getChannel().getUser().getId());
        return new VideoDto(vid.getId(),vid.getSrc(),vid.getTitle(),vid.getDescription(),vid.getPreview(),chan,vid.getCreatedAt());
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


    @PostMapping("/upload")
    public int icon(@RequestParam("video_inf")String vid_inf,@RequestParam("video") MultipartFile video,@RequestParam("preview") MultipartFile preview) {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(vid_inf));
        reader.setLenient(true);
        VideoRequest inf = gson.fromJson(reader, VideoRequest.class);

        if(inf.getChannel_id()!=0){
            String file_vid= UUID.randomUUID().toString();
            String file_prew= UUID.randomUUID().toString();
            service.store(video,file_vid);
            service.store(preview,file_prew);
            String ext_v= FilenameUtils.getExtension(video.getOriginalFilename());
            String ext_p= FilenameUtils.getExtension(preview.getOriginalFilename());
            Channel channel=chanrepository.findById(inf.getChannel_id()).get();
            Video vid=new Video(file_vid+'.'+ext_v,inf.getTitle(),inf.getDescription(),file_prew+'.'+ext_p,channel);
            repository.save(vid);
            return vid.getId();
        }
        else{
            return -1;
        }
    }
}
