package com.example.springboot.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private String text;
    private int video_id;
    private int user_id;
    private int reply_id;

    public CommentDto(int id, String text, int video_id, int user_id) {
        this.id = id;
        this.text = text;
        this.video_id = video_id;
        this.user_id = user_id;
        this.reply_id = 0;
    }

    public CommentDto(int id, String text, int video_id, int user_id, int reply_id) {
        this.id = id;
        this.text = text;
        this.video_id = video_id;
        this.user_id = user_id;
        this.reply_id = reply_id;
    }
}
