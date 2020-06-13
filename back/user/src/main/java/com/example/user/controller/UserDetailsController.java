package com.example.user.controller;

import com.example.user.dto.UserDetailsDTO;
import com.example.user.model.Notification;
import com.example.user.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("userDetails")
public class UserDetailsController {
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * GET /userDetails
     *
     * @return returns all object of type UserDetailsDTO
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getAllUserDetails() throws Exception {
        return new ResponseEntity<List>(userDetailsService.getAllUserDetails(), HttpStatus.OK);
    }

    /**
     * GET /userDetails/{id}
     *
     *
     *
     * @return returns object of type UserDetailsDTO
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDTO> getOneUserDetails(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userDetailsService.getOneUserDetails(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * POST /userDetails/{id}
     *
     * adding details to a user
     * consumes UserDetailsDTO
     * path variable is USER ID!
     *
     * @return returns notification
     */
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postOneUserDetails(@RequestBody UserDetailsDTO userDetailsDTO, @PathVariable String id) {
        try {
            userDetailsService.addNewUserDetails(id, userDetailsDTO);
            return new ResponseEntity<Notification>(new Notification("Adding user details to a user successfully done.", true), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<Notification>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<Notification>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * PUT /userDetails/{id}
     *
     * changing details of a user
     * consumes UserDetailsDTO
     * path variable is USER ID!
     *
     * @return returns notification
     */
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putOneUserDetails(@PathVariable String id, @RequestBody UserDetailsDTO userDetailsDTO) {
        try {
            userDetailsService.changeUserDetails(id, userDetailsDTO);
            return new ResponseEntity<Notification>(new Notification("Changing user details to a user successfully done.", true), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<Notification>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE /userDetails/{id}
     *
     * delete details of a user
     * consumes UserDetailsDTO
     *
     * UserDetailsDTO.id must be equal to user.userDetails.id
     *
     * @return returns notification
     */
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteOneUserDetails(@PathVariable String id, @RequestBody UserDetailsDTO userDetailsDTO) {
        try {
            userDetailsService.deleteUserDetails(id, userDetailsDTO);
            return new ResponseEntity<Notification>(new Notification("Deleting user details to a user successfully done.", true), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<Notification>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Notification>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }
}
