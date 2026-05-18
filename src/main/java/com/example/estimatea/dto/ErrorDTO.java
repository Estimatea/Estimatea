package com.example.estimatea.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorDTO {

    private final int status;
    private final String message;
    private final LocalDateTime timeStamp;

    public ErrorDTO(int status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
