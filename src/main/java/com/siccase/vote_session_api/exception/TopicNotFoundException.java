package com.siccase.vote_session_api.exception;

import java.util.UUID;

public class TopicNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Topic not found with id: %s";

    public TopicNotFoundException(UUID topicId) {
        super(String.format(MESSAGE, topicId));
    }
}
