package com.example.user.controller;

import com.example.user.dto.ChangeUsPwRequestDTO;
import com.example.user.dto.CreateUserRequestDTO;
import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.UserDTO;
import com.example.user.model.Notification;
import com.example.user.model.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    /**
     * GET /user/all
     *
     * @return returns object of type UserDTO with user id and username
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getAllUsers() throws Exception {
        return new ResponseEntity<List>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * GET /user?id={id}
     *
     * @return returns object of type UserDTO with user id and username
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getOneUser(@RequestParam(value="id", required = true) String id) throws Exception {
        try {
            return new ResponseEntity<UserDTO>(userService.getOneUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * POST /user
     *
     * @return returns notification
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postNewUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) throws Exception {
        try {
            userService.addNewUser(createUserRequestDTO);
            return new ResponseEntity<Notification>(new Notification("User " + createUserRequestDTO.getUsername() + " created.", true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * PUT /user
     *
     * @return returns notification
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putUser(@RequestBody ChangeUsPwRequestDTO changeUsPwRequestDTO) throws Exception {
        try {
            userService.changeUsPw(changeUsPwRequestDTO);
            return new ResponseEntity<Notification>(new Notification("User " + changeUsPwRequestDTO.getUsername() + " changed.", true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * DELETE /user?id={id}
     *
     * @return returns notification
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putUser(@RequestParam(value="id", required = true) String id) throws Exception {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<Notification>(new Notification("User with id " + id + " deleted.", true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }
}
