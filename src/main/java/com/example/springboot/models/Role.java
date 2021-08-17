package com.example.springboot.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="roles")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Role(String name) {
        this.name = name;
        users= new ArrayList<>();
    }

    @Column(nullable=false)
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<com.example.springboot.models.User> users;

    public Role() {
        users=new ArrayList<User>();
    }

    public String getName() {
        return name;
    }
}
