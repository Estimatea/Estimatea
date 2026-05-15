package com.example.estimatea.dto;

import java.time.LocalDateTime;

public class ErrorDTO {

    private final int status;
    private final String message;
    private final LocalDateTime timeStamp;

    public ErrorDTO(int status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
