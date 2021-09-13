package com.example.springboot.dto;

import lombok.Data;

@Data
public class ChannelDto {
    private int id;
    private String header_src;
    private String name;
    private int user_id;

    public ChannelDto(int id, String header_src, String name, int user_id) {
        this.id = id;
        this.header_src = header_src;
        this.name = name;
        this.user_id = user_id;
    }
}
