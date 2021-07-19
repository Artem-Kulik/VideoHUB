package com.example.springboot.repositories;

import com.example.springboot.models.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Integer> {
}
