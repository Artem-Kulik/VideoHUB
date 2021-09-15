package com.example.springboot.repositories;

import com.example.springboot.models.Video;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.List;

public interface VideoRepository  extends CrudRepository<Video, Integer> {
}
