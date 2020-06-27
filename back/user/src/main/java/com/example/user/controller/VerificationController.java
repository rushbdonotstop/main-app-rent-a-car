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

    Logger logger = LoggerFactory.getLogger(VerificationController.class);

    @GetMapping(value = "/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> confirmVerification(@PathVariable String token) throws Exception {
        try {
            if (verificationTokenService.confirmVerification(token) == null) {
                logger.warn("Unkown user tried to verify account with wrong token!");
                return new ResponseEntity<>(new Notification("Wrong token!", false), HttpStatus.BAD_REQUEST);
            } else {
                User user = verificationTokenService.confirmVerification(token);
                logger.info("User with username - {} successfully verified.", user.getUsername());
                return new ResponseEntity<>(new Notification("User successfully verified his account", true), HttpStatus.OK);
            }
        } catch(Exception e) {
            logger.error("User tried to verify his account, exception occured: {}", e.toString());
            return new ResponseEntity<>(new Notification("Server error", false), HttpStatus.BAD_REQUEST);
        }
    }
}
