package com.siccase.vote_session_api.service;

import com.siccase.vote_session_api.enums.SessionStatusEnum;
import com.siccase.vote_session_api.model.Topic;
import com.siccase.vote_session_api.repository.TopicRepository;
import com.siccase.vote_session_api.repository.VoteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TopicSchedule {
    private final TopicRepository topicRepository;
    private final VoteRepository voteRepository;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void finishTopics() {
        log.info("Initializing routine to finish topics");
        List<Topic> expiredTopics = topicRepository.findBySessionStatusAndFinishAtBefore(
                SessionStatusEnum.ACTIVE,
                LocalDateTime.now()
        );

        if (expiredTopics.isEmpty()) {
            log.info("No expired topics found. Stopping routine");
            return;
        }

        expiredTopics.forEach(topic -> {
            log.info("Cloding expired topic: {}", topic.getId());

            topic.setSessionStatus(SessionStatusEnum.FINISHED);
            topicRepository.save(topic);

            log.info("Routine to finish topics was concluded");

            // TODO: calculate result and send to queue
        });

        expiredTopics.forEach(topic -> {

        });
    }
}
