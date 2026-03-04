package com.siccase.vote_session_api.exception;

public class SessionNotFinishedException extends RuntimeException {
    public SessionNotFinishedException(String message) {
        super(message);
    }
}
