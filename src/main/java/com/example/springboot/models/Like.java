package com.example.springboot.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tbl_users",referencedColumnName = "id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tbl_videos",referencedColumnName = "id")
    private Video video;

    public Like() {
    }

    public Like(User user, Video video) {
        this.user = user;
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
