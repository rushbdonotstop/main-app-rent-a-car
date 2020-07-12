package com.example.user.controller;

import com.example.user.model.AgentRequest;
import com.example.user.model.Notification;
import com.example.user.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agentRequest")
public class AgentRequestController {
    @Autowired
    private RegisterService registerService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgentRequest>> getAllAgentRequests() {
        return new ResponseEntity<>(registerService.getAllAgentRequests(), HttpStatus.OK);
    }

    @PutMapping(value = "/{agentRequestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> approveAgentRequest(@PathVariable Long agentRequestId) throws Exception{
        registerService.approveAgent(agentRequestId);
        return new ResponseEntity<>(new Notification("Agent request successfully approved", true), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{agentRequestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteAgentRequest(@PathVariable Long agentRequestId) {
        registerService.deleteAgentRequest(agentRequestId);
        return new ResponseEntity<>(new Notification("Agent request successfully deleted", true), HttpStatus.OK);
    }
}
