package com.example.user.controller;

import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.TokenDTO;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        String jwt = "";
        System.out.println("POGODI OVAJ ENDPOINT");
        try {
            jwt = userService.login(loginRequestDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        System.out.println("PROSAO KAKO VALJA, TOKEN: " + jwt);
        return new ResponseEntity<>(new TokenDTO(jwt), HttpStatus.OK);
    }
}
