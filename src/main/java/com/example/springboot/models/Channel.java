package com.example.springboot.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name", nullable = false, length = 250)
    private String name;
    @Column(name="password", nullable = false, length = 250)
    private String header_src;

    public Channel(String name, String header_src, User user) {
        this.name = name;
        this.header_src = header_src;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tbl_users",referencedColumnName = "id")
    private User user;

    public Channel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader_src() {
        return header_src;
    }

    public void setHeader_src(String header_src) {
        this.header_src = header_src;
    }
}
