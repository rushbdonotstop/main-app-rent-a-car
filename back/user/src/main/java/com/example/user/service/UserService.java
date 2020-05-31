package com.example.user.service;

import com.example.user.dto.ChangeUsPwRequestDTO;
import com.example.user.dto.CreateUserRequestDTO;
import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.UserDTO;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
import com.example.user.repository.UserDetailsRepository;
import com.example.user.repository.UserPrivilegeRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserPrivilegeRepository userPrivilegeRepository;

    public Boolean userExists(String username) {
        return userRepository.findOneByUsername(username) != null;
    }

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

    public List<UserDTO> getAllUsers() throws Exception{
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User u: userList
             ) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(u.getId());
            userDTO.setUsername(u.getUsername());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public UserDTO getOneUser(String id) throws Exception{
        try {
            User user = userRepository.findOneById(Long.parseLong(id));
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername());
            return userDTO;
        } catch (ExceptionInInitializerError e) {
            throw new Exception("User with id " + id + " not found.");
        }
    }

    public void addNewUser(CreateUserRequestDTO createUserRequestDTO) throws Exception{
        if(userExists(createUserRequestDTO.getUsername())) {
            throw new Exception("Username already exists.");
        }

        User user = new User();
        user.setUsername(createUserRequestDTO.getUsername());
        user.setPassword(createUserRequestDTO.getPassword());

        UserDetails userDetails = new UserDetails();
        user.setUserDetails(userDetails);

        userDetailsRepository.save(userDetails);
        userRepository.save(user);
    }

    public void changeUsPw(ChangeUsPwRequestDTO changeUsPwRequestDTO) throws Exception {
        try {
            User user = userRepository.findOneById(changeUsPwRequestDTO.getId());

            user.setUsername(changeUsPwRequestDTO.getUsername());
            user.setPassword(changeUsPwRequestDTO.getPassword());

            userRepository.save(user);
        } catch (EntityNotFoundException e) {
            throw new Exception("Id doesn't exists.");
        }
    }

    public void deleteUser(String id) throws Exception{
        try {
            User user = userRepository.findOneById(Long.parseLong(id));

            userRepository.delete(user);
        } catch (EntityNotFoundException e) {
            throw new Exception("Id doesn't exists.");
        }
    }
}
