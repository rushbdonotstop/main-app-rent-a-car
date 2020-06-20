package com.example.user.controller;

import com.example.user.dto.PenaltyDTO;
import com.example.user.model.Penalty;
import com.example.user.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("penalty")
@RestController
public class PenaltyController {

    @Autowired
    private PenaltyService penaltyService;

    /**
     * POST /server/penalty
     *
     * @return returns status of new physical penalty creation
     */
    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PenaltyDTO> addPenalty(@RequestBody PenaltyDTO penalty) {
        try {
            System.out.println(penalty);
            Penalty newPenalty = this.penaltyService.addPenalty(penalty);
            return new ResponseEntity<>(penalty, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
