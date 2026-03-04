package com.siccase.vote_session_api.validation;

import com.siccase.vote_session_api.enums.SessionStatusEnum;
import com.siccase.vote_session_api.exception.SessionExpiredException;
import com.siccase.vote_session_api.exception.SessionNotActiveException;
import com.siccase.vote_session_api.exception.TopicNotFoundException;
import com.siccase.vote_session_api.model.Topic;
import com.siccase.vote_session_api.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TopicValidation {
    private final TopicRepository topicRepository;

    public Topic validateTopicIsActive(UUID id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));

        // TODO: consider call to close topic here

        if (topic.getSessionStatus() != SessionStatusEnum.ACTIVE) {
            throw new SessionNotActiveException("Topic session is not active");
        }

        if (topic.getFinishAt().isBefore(LocalDateTime.now())) {
            throw new SessionExpiredException("Session has ended");
        }

        return topic;
    }



}
