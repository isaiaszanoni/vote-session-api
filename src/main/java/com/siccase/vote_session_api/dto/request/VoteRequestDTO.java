package com.siccase.vote_session_api.dto.request;

import com.siccase.vote_session_api.enums.ResponseOptionsEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class VoteRequestDTO {
    private UUID topicId;
    private String memberCpf;
    private ResponseOptionsEnum vote;
}
