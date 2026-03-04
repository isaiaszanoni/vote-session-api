package com.siccase.vote_session_api.dto.response;

import com.siccase.vote_session_api.enums.DecisionOfTopicEnum;
import com.siccase.vote_session_api.enums.ResponseOptionsEnum;
import com.siccase.vote_session_api.enums.SessionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionResultDTO {
    private UUID topicId;
    private DecisionOfTopicEnum decision;
    private SessionStatusEnum sessionStatus;
    private long totalOfVotes;
    private double yesVotesPercent;
    private double noVotesPercent;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
}
