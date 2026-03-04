package com.siccase.vote_session_api.service;

import com.siccase.vote_session_api.dto.request.StartSessionDTO;
import com.siccase.vote_session_api.dto.request.TopicRequestDTO;
import com.siccase.vote_session_api.enums.SessionStatusEnum;
import com.siccase.vote_session_api.model.Topic;
import com.siccase.vote_session_api.repository.TopicRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicService {
    private static final long DEFAULT_DURATION_SESSION_MINUTES = 1;

    private final TopicRepository repository;

    // TODO: return TopicResponseDTO
    public void createTopic(TopicRequestDTO topic) {
        repository.findFirstByTitleAndSessionStatusNot(topic.getTitle(), SessionStatusEnum.FINISHED).ifPresent(existentTopic -> {
            if (existentTopic.getSessionStatus() == SessionStatusEnum.PENDING || existentTopic.getSessionStatus() == SessionStatusEnum.ACTIVE) {
                throw new IllegalStateException("Already exists another topic active or pending with the same title");
            }
        });

        Topic newTopic = Topic.builder()
                .title(topic.getTitle())
                .sessionStatus(SessionStatusEnum.PENDING)
                .build();

        repository.save(newTopic);
    }

    // TODO: return SessionResponseDTO
    public void startSession(StartSessionDTO startSession) {
        Topic topic = repository.findById(startSession.getTopicId())
                .orElseThrow(() -> new ObjectNotFoundException("Topic not find with id: ", startSession.getTopicId()));
        topicCanBeStarted(topic);

        topic.setFinishAt(calculateFinishDate(startSession.getDuration()));
        topic.setStartAt(LocalDateTime.now());
        topic.setSessionStatus(SessionStatusEnum.ACTIVE);

        log.info("Initializing session of topic: {}", topic.getId());
        repository.save(topic);
    }

    protected void topicCanBeStarted(Topic topic) {
        if (topic == null) {
            throw new IllegalArgumentException("Topic should not be null");
        }

        if (topic.getSessionStatus() == SessionStatusEnum.FINISHED) {
            throw new IllegalStateException("Topic is already finished. Please open another topic and session");
        }

        if (topic.getSessionStatus() == SessionStatusEnum.ACTIVE) {
            throw  new IllegalStateException("Topic is already active. Consider to vote in this session");
        }

        if (topic.getFinishAt() != null) {
            throw new IllegalStateException("Topic is already started");
        }
    }

    public LocalDateTime calculateFinishDate(StartSessionDTO.Duration duration) {
        LocalDateTime now = LocalDateTime.now();

        if (duration == null || duration.getValue() == null || duration.getTimeUnit() == null) {
            log.info("Received session with invalid/incomplete value to duration... " +
                    "setting the default time: {} minute", DEFAULT_DURATION_SESSION_MINUTES);
            return now.plusMinutes(DEFAULT_DURATION_SESSION_MINUTES);
        }

        if (duration.getValue() <= 0) {
            throw new IllegalArgumentException("Duration should have value greater than zero");
        }

        return switch (duration.getTimeUnit()) {
            case MINUTE -> now.plusMinutes(duration.getValue());
            case HOUR -> now.plusHours(duration.getValue());
            case DAY -> now.plusDays(duration.getValue());
        };
    }
}
