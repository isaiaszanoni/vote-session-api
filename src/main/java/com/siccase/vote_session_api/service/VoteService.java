package com.siccase.vote_session_api.service;

import com.siccase.vote_session_api.dto.request.VoteRequestDTO;
import com.siccase.vote_session_api.dto.response.VoteResponseDTO;
import com.siccase.vote_session_api.exception.MemberAlreadyVoteException;
import com.siccase.vote_session_api.model.Topic;
import com.siccase.vote_session_api.model.Vote;
import com.siccase.vote_session_api.repository.VoteRepository;
import com.siccase.vote_session_api.validation.TopicValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository repository;

    private final TopicValidation topicValidation;

    public Optional<Vote> getVoteByMemberCpfAndTopicId(String memberCpf, UUID topicId) {
        return repository.findByMemberCpfAndTopicId(memberCpf,topicId);
    }

    public VoteResponseDTO registerVote(VoteRequestDTO voteRequest) {
        Topic topic = topicValidation.validateTopicIsActive(voteRequest.getTopicId());

        getVoteByMemberCpfAndTopicId(voteRequest.getMemberCpf(), voteRequest.getTopicId()).ifPresent(existentVote -> {
            throw new MemberAlreadyVoteException();
        });

        Vote vote = Vote.builder()
                .vote(voteRequest.getVote())
                .topicId(topic.getId())
                .memberCpf(voteRequest.getMemberCpf())
                .build();

        Vote voteSaved = repository.save(vote);
        return new VoteResponseDTO(voteSaved.getMemberCpf(), voteSaved.getVote(), voteSaved.getCreatedAt());
    }
}
