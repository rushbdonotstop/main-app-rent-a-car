package com.example.location.service;

import com.example.location.model.Notification;
import com.example.location.repository.StateRepository;
import com.example.location.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<State> getAll() {
        List<State> states = null;
        try {
            states = stateRepository.findAll();
        }
        catch (Exception e){

        }
        return states;
    }

    public State get(Long stateId) {
        State state = null;
        try {
            if (stateRepository.findById(stateId).isPresent()){
                state = stateRepository.findById(stateId).get();
            }
        }
        catch (Exception e){

        }
        return state;
    }

    public Notification delete(Long stateId) {
        Notification notification = new Notification("Deleting state failed.");
        try {
            if (stateRepository.findById(stateId).isPresent()){
                stateRepository.deleteById(stateId);
                notification.setText("Deleted state.");
            }
            else{
                notification.setText("State id does not exist.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }

    public Notification create(State state) {
        Notification notification = new Notification("Creating state failed.");
        try {
            if (stateRepository.findByValue(state.getValue()) == null){
                stateRepository.save(state);
                notification.setText("Created state.");
            }
            else{
                notification.setText("State already exists.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }
}
