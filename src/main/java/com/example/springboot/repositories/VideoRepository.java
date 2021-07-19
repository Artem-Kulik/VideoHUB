package com.example.springboot.repositories;

import com.example.springboot.models.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository  extends CrudRepository<Video, Integer> {
}
