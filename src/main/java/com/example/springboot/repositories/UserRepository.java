package com.example.springboot.repositories;

import com.example.springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<com.example.springboot.models.User, Long>, JpaSpecificationExecutor<com.example.springboot.models.User>{
    com.example.springboot.models.User findByName(String username);
}
