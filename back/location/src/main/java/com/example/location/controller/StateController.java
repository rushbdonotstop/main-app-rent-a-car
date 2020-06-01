package com.example.location.controller;

import com.example.location.model.City;
import com.example.location.model.Notification;
import com.example.location.model.State;
import com.example.location.service.CityService;
import com.example.location.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("location/state")
public class StateController {

    @Autowired
    private StateService stateService;

    /**
     * GET /server/location/state
     *
     * @return return all states
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<State>> getAll() throws Exception {
        List<State> states = stateService.getAll();
        return new ResponseEntity<List<State>>(states, HttpStatus.OK);
    }

    /**
     * GET /server/location/state/{id}
     *
     * @return return state with specific id
     */
    @GetMapping(value = "/{stateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<State> get(@PathVariable Long stateId) throws Exception {
        State state = stateService.get(stateId);
        return new ResponseEntity<State>(state, HttpStatus.OK);
    }

    /**
     * DELETE /server/location/state/{id}
     *
     * @return return status of delete state action
     */
    @DeleteMapping(value = "/{stateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> delete(@PathVariable Long stateId) throws Exception {
        Notification notification = stateService.delete(stateId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * CREATE /server/location/city
     *
     * @return return status of create state
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> create(@RequestBody State state) throws Exception {
        Notification notification = stateService.create(state);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }
}
