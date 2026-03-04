package com.siccase.vote_session_api.repository;

import com.siccase.vote_session_api.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
    Optional<Vote> findByMemberCpfAndTopicId(String memberCpf, UUID topicId);
}
