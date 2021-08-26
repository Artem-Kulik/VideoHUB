package com.example.springboot.dto;

public class ResponseDto {
    private String message;

    public ResponseDto(String message) {
        this.message = message;
    }

    private Object object;

    public ResponseDto(String message, Object object) {
        this.message = message;
        this.object = object;
    }
}
