package com.siccase.vote_session_api.controller;

import com.siccase.vote_session_api.dto.request.StartSessionDTO;
import com.siccase.vote_session_api.dto.request.TopicRequestDTO;
import com.siccase.vote_session_api.dto.response.ResponseDTO;
import com.siccase.vote_session_api.dto.response.SessionResponseDTO;
import com.siccase.vote_session_api.dto.response.TopicResponseDTO;
import com.siccase.vote_session_api.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/topics")
@AllArgsConstructor
public class TopicControllerV1 {
    @Autowired
    private final TopicService service;

    @PostMapping()
    public ResponseEntity<ResponseDTO> createTopic(@RequestBody TopicRequestDTO topic) {
        TopicResponseDTO response = service.createTopic(topic);
        return ResponseEntity.ok(
                new ResponseDTO(201, "Topic created successfully", LocalDateTime.now(), response));
    }

    @PostMapping("/start-session")
    public ResponseEntity<ResponseDTO> startSession(@RequestBody StartSessionDTO startSessionDTO) {
        SessionResponseDTO response = service.startSession(startSessionDTO);
        return ResponseEntity.ok(
                new ResponseDTO(200, "Session started successfully", LocalDateTime.now(), response));
    }
}
