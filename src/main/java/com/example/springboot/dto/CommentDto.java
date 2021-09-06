package com.example.springboot.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private String text;
    private int video_id;
    private int user_id;

    public CommentDto(int id, String text, int video_id, int user_id) {
        this.id = id;
        this.text = text;
        this.video_id = video_id;
        this.user_id = user_id;
    }
}
