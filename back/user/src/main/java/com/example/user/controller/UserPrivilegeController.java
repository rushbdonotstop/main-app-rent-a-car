package com.example.user.controller;

import com.example.user.dto.UserPrivilegeDTO;
import com.example.user.dto.UserPrivilegeRequestDTO;
import com.example.user.model.Notification;
import com.example.user.service.UserPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController
@RequestMapping("userPrivilege")
public class UserPrivilegeController {
    @Autowired
    private UserPrivilegeService userPrivilegeService;

    /**
     * GET /userPrivilege
     *
     * @return returns all object of type UserPrivilegeDTO
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getAllUserPrivileges() {
        return new ResponseEntity<>(userPrivilegeService.getAllUserPrivileges(), HttpStatus.OK);
    }

    /**
     * GET /userPrivilege/{id}
     *
     * id is user id!
     *
     * @return returns one user privileges
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPrivilegeDTO> getOneUserPrivileges(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userPrivilegeService.getOneUserPrivileges(id), HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * POST /userPrivilege/{id}
     *
     * id is user id!
     * consumes object type UserPrivilegeRequestDTO
     *
     * @return returns notification
     */
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> addUserPrivilege(@RequestBody UserPrivilegeRequestDTO userPrivilegeRequestDTO, @PathVariable String id) {
        try {
            userPrivilegeService.addPrivilege(id, userPrivilegeRequestDTO);
            return new ResponseEntity<>(new Notification("User privilege added.", true), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new Notification(e.getMessage(),false), HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * DELETE /userPrivilege/{id}/{privilege}
     *
     * id is user id!
     * consumes object type UserPrivilegeRequestDTO
     *
     * @return returns notification
     */
    @DeleteMapping(value = "{id}/{privilege}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteUserPrivilege(@PathVariable String id, @PathVariable String privilege) {
        try {
            UserPrivilegeRequestDTO userPrivilegeRequestDTO = new UserPrivilegeRequestDTO();
            userPrivilegeRequestDTO.setUserPrivilege(privilege);
            userPrivilegeService.deleteUserPrivilege(id, userPrivilegeRequestDTO);
            return new ResponseEntity<>(new Notification("User privilege deleted.", true), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new Notification(e.getMessage(),false), HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }
}
