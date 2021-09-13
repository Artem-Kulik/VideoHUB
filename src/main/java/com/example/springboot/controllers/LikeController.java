package com.example.springboot.controllers;

import com.example.springboot.dto.CommentDto;
import com.example.springboot.models.Comment;
import com.example.springboot.models.Like;
import com.example.springboot.models.User;
import com.example.springboot.models.Video;
import com.example.springboot.repositories.LikeRepository;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeRepository repository;
    private final VideoRepository vidrepository;
    private final UserRepository userRepository;
    @Autowired
    public LikeController(LikeRepository repository,VideoRepository vidrepository,UserRepository userRepository) {
        this.repository = repository;
        this.vidrepository = vidrepository;
        this.userRepository = userRepository;
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
//    @PostMapping("/add")
//    public String add(@RequestBody Like like) {
//        repository.save(like);
//        return "Success!";
//    }
//    @PostMapping("/update/{id}")
//    public String update(@PathVariable int id,@RequestBody Like like) {
//        like.setId(id);
//        repository.save(like);
//        return "Success!";
//    }
    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        repository.delete(repository.findById(id).get());
        return "Success!";
    }
    @GetMapping("/getvideo/{id}")
    public int getVideoLikes(@PathVariable int id){
        Video search = vidrepository.findById(id).get();
        int res=repository.findAllByVideo(search).size();
        return res;
    }
    @GetMapping("/add-video/{id}&{usr}")
    public int addLike(@PathVariable int id,@PathVariable String usr){
        Video search = vidrepository.findById(id).get();
        User user= userRepository.findByName(usr);
        List<Like> likes=repository.findAllByVideo(search);
        boolean islike=false;
        for (Like like:
                likes) {
            if(like.getUser().getName()==user.getName())
                islike=true;
        }
        if(!islike){
            Like new_like=new Like(user,search);
            repository.save(new_like);
        }

        int res=repository.findAllByVideo(search).size();
        return res;
    }
    @GetMapping("/remove-video/{id}&{usr}")
    public int removeLike(@PathVariable int id,@PathVariable String usr){
        Video search = vidrepository.findById(id).get();
        User user= userRepository.findByName(usr);
        List<Like> likes=repository.findAllByVideo(search);
        Like liked_vid=null;
        for (Like like:
                likes) {
            if(like.getUser().getName()==user.getName())
                liked_vid=like;
        }
        if(liked_vid!=null){
            repository.delete(liked_vid);
        }

        int res=repository.findAllByVideo(search).size();
        return res;
    }
    @GetMapping("/liked-video/{id}&{usr}")
    public boolean isLiked(@PathVariable int id,@PathVariable String usr){
        Video search = vidrepository.findById(id).get();
        User user= userRepository.findByName(usr);
        List<Like> likes=repository.findAllByVideo(search);
        boolean islike=false;
        for (Like like:
                likes) {
            if(like.getUser().getName()==user.getName())
                islike=true;
        }
        return islike;
    }
}
