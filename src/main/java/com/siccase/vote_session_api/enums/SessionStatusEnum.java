package com.siccase.vote_session_api.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SessionStatusEnum {
    PENDING("Pending"),
    ACTIVE("Active"),
    FINISHED("Finished");

    private final String value;

    public String value() {
        return value;
    }
}
