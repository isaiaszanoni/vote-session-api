package com.siccase.vote_session_api.controller;

import com.siccase.vote_session_api.dto.request.StartSessionDTO;
import com.siccase.vote_session_api.dto.request.TopicRequestDTO;
import com.siccase.vote_session_api.dto.response.ResponseDTO;
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
@RequestMapping("api/")
@AllArgsConstructor
public class TopicController {
    @Autowired
    private final TopicService service;

    @PostMapping("v1/topic")
    // @ResponseStatus
    public ResponseEntity<ResponseDTO> createTopic(@RequestBody TopicRequestDTO topic) {
        service.createTopic(topic);
        return ResponseEntity.ok(new ResponseDTO(201, "Topic created successfully", LocalDateTime.now(), null));
    }

    @PostMapping("v1/topic/start-session")
    public ResponseEntity<?> startSession(@RequestBody StartSessionDTO startSessionDTO) {
        service.startSession(startSessionDTO);
        return ResponseEntity.ok().build();
    }
}
