package com.siccase.vote_session_api.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String message) {
        super(message);
    }
}
