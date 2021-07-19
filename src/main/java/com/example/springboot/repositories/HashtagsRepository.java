package com.example.springboot.repositories;

import com.example.springboot.models.Hashtags;
import org.springframework.data.repository.CrudRepository;

public interface HashtagsRepository extends CrudRepository<Hashtags, Integer> {
}
