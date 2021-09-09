package com.example.springboot.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="src", nullable = false, length = 1024)
    private String src;
    @Column(name="title", nullable = false, length = 250)
    private String title;
    @Column(name="description", nullable = false, length = 250)
    private String description;
    @Column(name="preview", nullable = false, length = 1024)
    private String preview;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="tbl_channel",referencedColumnName = "id")
    private Channel channel;

    public Video() {

    }

    public Video(String src, String title, String description, String preview, Channel channel) {
        this.src = src;
        this.title = title;
        this.description = description;
        this.preview = preview;
        this.channel = channel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }


}
