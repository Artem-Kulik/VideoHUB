package com.example.springboot.repositories;

import com.example.springboot.models.Channel;
import com.example.springboot.models.Subscriber;
import org.springframework.data.repository.CrudRepository;

public interface SubscriberRepository extends CrudRepository<Subscriber, Integer> {
}
