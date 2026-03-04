package com.siccase.vote_session_api.dto.response;

import com.siccase.vote_session_api.enums.ResponseOptionsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VoteResponseDTO {
    private String memberCpf;
    private ResponseOptionsEnum vote;
    private LocalDateTime createdAt;
}
