package com.example.springboot.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_hashtags")
public class Hashtags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tbl_videos",referencedColumnName = "id")
    private Video video;
    @Column(name="text", nullable = false, length = 250)
    private String text;

    public Hashtags() {
    }

    public Hashtags(Video video, String text) {
        this.video = video;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
