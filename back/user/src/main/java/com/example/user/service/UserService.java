package com.example.user.service;

import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.UserCreateVehicleDTO;
import com.example.user.dto.UserDTO;
import com.example.user.model.Notification;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
import com.example.user.model.UserPrivilege;
import com.example.user.model.enums.Privilege;
import com.example.user.model.enums.UserType;
import com.example.user.repository.UserDetailsRepository;
import com.example.user.repository.UserPrivilegeRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean canUserCreate(Long userId) {
        try{
            if (userRepository.findById(userId).isPresent()){
                User u = userRepository.findById(userId).get();
                System.out.println("FOUND? " + userPrivilegeRepository.findByUserAndPrivilege(u, Privilege.ADD_VEHICLE));
                if((u.getUserDetails().getUserType().equals(UserType.END_USER) && u.getUserDetails().getVehicleNum() == 3)
                || userPrivilegeRepository.findByUserAndPrivilege(u, Privilege.ADD_VEHICLE) == null){
                    return false;
                }
                else{
                    return true;
                }
            }
        }
        catch(Exception e){

        }

        return false;
    }

    public Notification updateUserVehicleNumAfterCreate(Long userId) {
        Notification notification = new Notification("Failed to update user vehicle number after create.");
        try{
            if (userRepository.findById(userId).isPresent()){
                User u = userRepository.findById(userId).get();

                if (userDetailsRepository.findById(u.getUserDetails().getId()).isPresent()){
                    UserDetails userDetails = userDetailsRepository.findById(u.getUserDetails().getId()).get();
                    userDetails.setVehicleNum(userDetails.getVehicleNum() + 1);
                    userDetailsRepository.save(userDetails);
                    notification.setText("Updated user vehicle number after create.");
                    if (userDetails.getUserType().equals(UserType.END_USER) && userDetails.getVehicleNum() == 3){
                        UserPrivilege userPrivilege = userPrivilegeRepository.findByUserAndPrivilege(u, Privilege.ADD_VEHICLE);
                        userPrivilegeRepository.deleteById(userPrivilege.getId());
                        notification.setText("Updated user vehicle number after create. User reached max number of vehicles.");
                    }
                }
                else{
                    notification.setText("User details id does not exist.");
                }
            }
            else{
                notification.setText("User id does not exist.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }

    public User loginTest(LoginRequestDTO loginRequestDTO) {
        try{
            return userRepository.findByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        }
        catch(Exception e){

        }
        return null;
    }
}
