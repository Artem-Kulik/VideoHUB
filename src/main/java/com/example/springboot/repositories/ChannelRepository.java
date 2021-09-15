package com.example.springboot.repositories;

import com.example.springboot.models.Channel;
import com.example.springboot.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<Channel, Integer> {
    Channel findByUser(User user);
}
