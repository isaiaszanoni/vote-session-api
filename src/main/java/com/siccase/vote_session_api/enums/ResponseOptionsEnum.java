package com.siccase.vote_session_api.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResponseOptionsEnum {
    SIM("Sim"),
    NAO("Não");

    private final String value;

    public String value() {
        return value;
    }
}
