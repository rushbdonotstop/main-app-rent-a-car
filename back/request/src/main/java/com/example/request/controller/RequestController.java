package com.example.request.controller;

import com.example.request.DTO.RequestDTO;
import com.example.request.model.Request;
import com.example.request.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    RequestService requestService;

    /**
     * GET /server/request
     *
     * @return return all requests
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Request>> getAll() {
        return new ResponseEntity<>(this.requestService.findAll(), HttpStatus.OK);
    }

    /**
     * PUT /server/request/{id}
     *
     * @return returns status of request update
     */
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Request> updateRequest(@PathVariable("id") Long id, @RequestBody Request request) {
        Request newRequest = this.requestService.update(id, request);
        if (newRequest != null)
            return new ResponseEntity<>(newRequest, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * POST /server/request/{id}
     *
     * @return returns status of new request creation
     */
    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> newRequest(@RequestBody List<RequestDTO> requests) {
        boolean status = this.requestService.addRequest(requests);
        if (status){
            return new ResponseEntity<>("Request successfully created", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Request creation failed", HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * DELETE /server/request/{id}
     *
     * @return returns status of request deletion
     */
    @DeleteMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id) {
        boolean status = this.requestService.delete(id);
        if (status)
            return new ResponseEntity<>("Success", HttpStatus.OK);
        else
            return new ResponseEntity<>("Request not found", HttpStatus.NOT_FOUND);
    }
}
