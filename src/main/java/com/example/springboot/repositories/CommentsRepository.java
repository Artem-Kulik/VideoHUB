package com.example.springboot.repositories;

import com.example.springboot.models.Comment;
import com.example.springboot.models.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByVideo(Video video);
}
