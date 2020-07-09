package com.example.user.controller;

import com.example.user.dto.EmailDTO;
import com.example.user.model.Notification;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
import com.example.user.model.enums.UserType;
import com.example.user.service.RegisterService;
import com.example.user.service.UserDetailsService;
import com.example.user.service.UserService;
import com.example.user.service.VerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    RegisterService registerService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> register(@RequestBody User user) {

            if (registerService.validate(user)) {
                try {
                EmailDTO email = registerService.registerUser(user);

                String response = (this.sendVerificationMail(email).getBody());
                return new ResponseEntity<>(new Notification("You registered successfully, you will get verification mail soon!", true), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
            }
            } else {
                return new ResponseEntity<>(new Notification("User input didn't pass server valdiation!", false), HttpStatus.BAD_REQUEST);
            }

    }

    public ResponseEntity<String> sendVerificationMail(EmailDTO emailDTO) {
        try {
            String response = restTemplate.postForEntity("https://fine-email-service.herokuapp.com/email/send",
                    new HttpEntity<EmailDTO>(emailDTO), String.class).getBody();
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Server error", HttpStatus.BAD_REQUEST);
        }
    }

}
