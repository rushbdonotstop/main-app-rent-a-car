package com.example.user.service;

import com.example.user.dto.PenaltyDTO;
import com.example.user.model.Penalty;
import com.example.user.model.enums.PenaltyStatus;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
import com.example.user.repository.PenaltyRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaltyService {

    @Autowired
    private PenaltyRepository penaltyRepository;

    @Autowired
    private UserRepository userRepository;

    public Penalty addPenalty(PenaltyDTO penaltyDTO) {
        User user = this.userRepository.findOneById(penaltyDTO.getUserId());
        UserDetails userDetails = user.getUserDetails();
        Penalty penalty = new Penalty();
        penalty.setTotal(penaltyDTO.getTotal());
        penalty.setPenaltyStatus(PenaltyStatus.NOT_PAID);
        penalty.setId(null);
        Penalty newPenalty = this.penaltyRepository.save(penalty);
        userDetails.getPenalties().add(newPenalty);
        this.userRepository.save(user);

        return penalty;
    }

    private Optional<Penalty> findById(Long id) {
        return this.penaltyRepository.findById(id);
    }

    public List<Penalty> findAll() {
        return this.penaltyRepository.findAll();
    }

    public List<Penalty> unpaidPenalties(Long userId) {
        return this.penaltyRepository.unpaidPenalties(userId);
    }

}
