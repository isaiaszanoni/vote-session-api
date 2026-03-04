package com.siccase.vote_session_api.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TimeUnitEnum {
    MINUTE("Minute"),
    HOUR("Hour"),
    DAY("Day");

    private final String value;

    public String value() {
        return value;
    }
}
