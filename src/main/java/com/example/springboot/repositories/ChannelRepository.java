package com.example.springboot.repositories;

import com.example.springboot.models.Channel;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<Channel, Integer> {
}
