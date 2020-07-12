package com.example.user.service;

import com.example.user.dto.UserDetailsDTO;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
import com.example.user.model.enums.UserType;
import com.example.user.repository.UserDetailsRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    public List getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public UserDetailsDTO getOneUserDetails(String id) throws Exception {
        try {
            UserDetails userDetails = userDetailsRepository.getOne(Long.parseLong(id));

            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(userDetails.getId(), userDetails.getFullName(), userDetails.getAddress()
                    , userDetails.getBusinessNum(), userDetails.getVehicleNum(), userDetails.getUserType().toString(), userDetails.getEmail());

            return userDetailsDTO;
        } catch (EntityNotFoundException e) {
            throw new Exception("Cant find details id = " + id);
        }
    }

    public void addNewUserDetails(String id, UserDetailsDTO userDetailsDTO) throws Exception, EntityNotFoundException{
        try {
            User user = userRepository.findOneById(Long.parseLong(id));
            if(user.getUserDetails() != null) {
                throw new Exception("User already has user details. Try sending put request if you want to change details");
            }

            UserDetails userDetails = new UserDetails();
            userDetails.setAddress(userDetailsDTO.getAddress());
            userDetails.setBusinessNum(userDetailsDTO.getBusinessNum());
            userDetails.setFullName(userDetailsDTO.getFullName());
            userDetails.setUserType(UserType.toEnum(userDetailsDTO.getUserType()));
            userDetails.setVehicleNum(userDetailsDTO.getVehicleNum());
            user.setUserDetails(userDetails);

            userDetailsRepository.save(userDetails);
            userRepository.save(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User doesn't exist");
        }
    }

    public void changeUserDetails(String id, UserDetailsDTO userDetailsDTO) throws EntityNotFoundException {
        User user;
        try {
            user = userRepository.findOneById(Long.parseLong(id));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User not found");
        }
        UserDetails userDetails;
        try {
            userDetails = userDetailsRepository.getOne(user.getUserDetails().getId());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User details not found");
        }

        userDetails.setAddress(userDetailsDTO.getAddress());
        userDetails.setBusinessNum(userDetailsDTO.getBusinessNum());
        userDetails.setFullName(userDetailsDTO.getFullName());
        userDetails.setUserType(UserType.toEnum(userDetailsDTO.getUserType()));
        userDetails.setVehicleNum(userDetailsDTO.getVehicleNum());

        userDetailsRepository.save(userDetails);
    }

    public void deleteUserDetails(String id, UserDetailsDTO userDetailsDTO) throws Exception, EntityNotFoundException{
        User user;
        try {
            user = userRepository.findOneById(Long.parseLong(id));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User not found");
        }
        UserDetails userDetails;
        try {
            userDetails = userDetailsRepository.getOne(user.getUserDetails().getId());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User details not found");
        }
        if(user.getUserDetails().getId()!= userDetailsDTO.getId()) {
            throw new Exception("Id mismatch!");
        }

        user.setUserDetails(null);
        userDetailsRepository.delete(userDetails);
        userRepository.save(user);
    }
}
