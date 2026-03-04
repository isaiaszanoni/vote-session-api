package com.siccase.vote_session_api.dto.response;

import com.siccase.vote_session_api.enums.SessionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TopicResponseDTO {
    private UUID id;
    private String title;
    private SessionStatusEnum sessionStatus;
}
