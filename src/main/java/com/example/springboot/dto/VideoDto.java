package com.example.springboot.dto;

import lombok.Data;

@Data
public class VideoDto {
    private int id;
    private String src;
    private String title;
    private String description;
    private String preview;
    private int channel_id;

    public VideoDto(int id, String src, String title, String description, String preview, int channel_id) {
        this.id = id;
        this.src = src;
        this.title = title;
        this.description = description;
        this.preview = preview;
        this.channel_id = channel_id;
    }
}
