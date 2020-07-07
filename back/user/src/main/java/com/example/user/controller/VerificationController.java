package com.example.user.controller;

import com.example.user.dto.EmailDTO;
import com.example.user.model.Notification;
import com.example.user.model.User;
import com.example.user.service.VerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("verification")
public class VerificationController {

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> confirmVerification(@PathVariable String token) throws Exception {
        try {
            if (verificationTokenService.confirmVerification(token) == null) {
                return new ResponseEntity<>(new Notification("Wrong token!", false), HttpStatus.BAD_REQUEST);
            } else {
                User user = verificationTokenService.confirmVerification(token);
                return new ResponseEntity<>(new Notification("User successfully verified his account", true), HttpStatus.OK);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(new Notification("Server error", false), HttpStatus.BAD_REQUEST);
        }
    }
}
