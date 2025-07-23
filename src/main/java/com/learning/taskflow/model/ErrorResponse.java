package com.learning.taskflow.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    
    private String error;
    private String message;
    private LocalDateTime time;
    
}
