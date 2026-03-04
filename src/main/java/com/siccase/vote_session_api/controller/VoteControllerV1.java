package com.siccase.vote_session_api.controller;

import com.siccase.vote_session_api.dto.request.VoteRequestDTO;
import com.siccase.vote_session_api.dto.response.ResponseDTO;
import com.siccase.vote_session_api.dto.response.VoteResponseDTO;
import com.siccase.vote_session_api.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/votes")
@AllArgsConstructor
public class VoteControllerV1 {
    @Autowired
    private final VoteService service;

    @PostMapping()
    public ResponseEntity<ResponseDTO> vote(@RequestBody VoteRequestDTO voteRequest) {
        VoteResponseDTO response = service.registerVote(voteRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(201, "Vote computed successfully", LocalDateTime.now(), response));
    }
}
