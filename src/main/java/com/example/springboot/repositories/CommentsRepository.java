package com.example.springboot.repositories;

import com.example.springboot.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comment, Integer> {
}
