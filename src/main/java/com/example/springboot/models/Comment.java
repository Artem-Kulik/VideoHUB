package com.example.springboot.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="text", nullable = false, length = 250)
    private String text;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="tbl_users",referencedColumnName = "id")
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="tbl_videos",referencedColumnName = "id")
    private Video video;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="tbl_comments",referencedColumnName = "id",nullable = true)
    private Comment reply;

    public Comment() {
    }

    public Comment(String text,User user, Video video, Comment reply) {
        this.text = text;
        this.user = user;
        this.video = video;
        this.reply = reply;
    }

    public Comment(String text,User user, Video video) {
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
