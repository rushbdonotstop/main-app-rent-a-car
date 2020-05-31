package com.example.user.controller;

import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.UserDTO;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * GET /user/userExists
     *
     * @return boolean value which indicates user existence
     */
    @GetMapping(value = "/userExists", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> userExists(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        Boolean userExists = userService.userExists(loginRequestDTO);
        return new ResponseEntity<Boolean>(userExists, HttpStatus.OK);
    }

    /**
     * GET /user/username
     *
     * @return returns object of type UserDTO with user id and username
     */
    @GetMapping(value = "/username", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUsername(@RequestBody UserDTO userDTO) throws Exception {
        UserDTO userInfo = userService.getUsername(userDTO);
        return new ResponseEntity<UserDTO>(userInfo, HttpStatus.OK);
    }
}
