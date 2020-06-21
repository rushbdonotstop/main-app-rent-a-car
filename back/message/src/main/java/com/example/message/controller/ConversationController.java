package com.example.message.controller;

import com.example.message.DTO.ConversationDTO;
import com.example.message.DTO.UserDTO;
import com.example.message.model.Conversation;
import com.example.message.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("conversation")
public class ConversationController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ConversationService conversationService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Conversation>> getAllConversations() throws Exception {
        System.out.println("USAO U PRVU");
        return new ResponseEntity<List<Conversation>>(conversationService.findAll(), HttpStatus.OK);

    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConversationDTO>> getConversations(@PathVariable Long userId) throws Exception {
        System.out.println("USAO U DRUGU");
        List<UserDTO> userList = (this.getUsernames()).getBody();

        return new ResponseEntity<List<ConversationDTO>>(conversationService.getConversationForUser(userId, userList), HttpStatus.OK);

    }

    public ResponseEntity<List<UserDTO>> getUsernames() throws Exception {
        System.out.println("Getting all usernames");
        List<UserDTO> response = restTemplate.exchange("http://user/user/usernames/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {}).getBody();

        return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
    }
}
