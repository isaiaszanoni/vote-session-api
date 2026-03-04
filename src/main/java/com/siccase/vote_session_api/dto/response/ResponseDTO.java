package com.siccase.vote_session_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ResponseDTO {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Object data;
}