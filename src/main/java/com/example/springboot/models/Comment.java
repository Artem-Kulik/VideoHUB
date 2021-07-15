package com.example.springboot.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_comments")
public class Comment {
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tbl_comments",referencedColumnName = "id",nullable = true)
    private Comment reply;

    public Comment() {
    }

    public Comment(User user, Video video, Comment reply) {
        this.user = user;
        this.video = video;
        this.reply = reply;
    }

    public Comment(User user, Video video) {
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

    public Comment getReply() {
        return reply;
    }

    public void setReply(Comment reply) {
        this.reply = reply;
    }
}
