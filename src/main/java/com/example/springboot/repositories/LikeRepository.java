package com.example.springboot.repositories;

import com.example.springboot.models.Comment;
import com.example.springboot.models.Like;
import com.example.springboot.models.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeRepository extends CrudRepository<Like, Integer> {
    List<Like> findAllByVideo(Video video);
}
