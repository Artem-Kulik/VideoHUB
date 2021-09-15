package com.example.springboot.dto;

import lombok.Data;

@Data
public class VideoRequest {
    private String title;
    private String description;
    private int channel_id;

    public VideoRequest(String title, String description, int channel_id) {
        this.title = title;
        this.description = description;
        this.channel_id = channel_id;
    }
}
