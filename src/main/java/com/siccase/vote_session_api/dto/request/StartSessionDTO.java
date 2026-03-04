package com.siccase.vote_session_api.dto.request;

import com.siccase.vote_session_api.enums.TimeUnitEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartSessionDTO {
    UUID topicId;
    Duration duration;

    @Data
    public static class Duration {
        TimeUnitEnum timeUnit;
        Long value;
    }
}

