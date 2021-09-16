package com.example.springboot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VideoDto {
    private int id;
    private String src;
    private String title;
    private String description;
    private String preview;
    private ChannelDto channel;
    private Date created_at;

    public VideoDto(int id, String src, String title, String description, String preview, ChannelDto channel,Date created_at) {
        this.id = id;
        this.src = src;
        this.title = title;
        this.description = description;
        this.preview = preview;
        this.channel = channel;
        this.created_at = created_at;
    }
}
