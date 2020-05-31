package com.example.user.service;

import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.UserDTO;
import com.example.user.model.UserPrivilege;
import com.example.user.repository.UserDetailsRepository;
import com.example.user.repository.UserPrivilegeRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserPrivilegeRepository userPrivilegeRepository;

    public Boolean userExists(LoginRequestDTO loginRequestDTO) {
        return userRepository.findByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()) != null;
    }

    public UserDTO getUsername(UserDTO userDTO) {
        try{
            if(userRepository.findById(userDTO.getId()).isPresent()){
                userDTO.setUsername(userRepository.findById(userDTO.getId()).get().getUsername());
            }
            else{
                userDTO.setUsername("User does not exist or wrong id.");
            }
        }
        catch (Exception e){
            userDTO.setUsername("Request for user username failed.");
        }
        return userDTO;
    }
}
