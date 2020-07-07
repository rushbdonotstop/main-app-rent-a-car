package com.example.user.service;

import com.example.user.dto.ChangeUsPwRequestDTO;
import com.example.user.dto.CreateUserRequestDTO;
import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.UserCreateVehicleDTO;
import com.example.user.dto.UserDTO;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
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
import javax.persistence.EntityNotFoundException;
import java.util.*;

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

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<UserDTO> convertUserToUserDTO(List<User> list) {
        List<UserDTO> newList = new ArrayList<>();
        for(User user : list) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername());
            newList.add(userDTO);
        }
        return newList;
    }

    public UserDTO getUsername(Long userId) {
        UserDTO userDTO = new UserDTO();
        try{
            if(userRepository.findById(userId).isPresent()){
                userDTO.setId(userId);
                userDTO.setUsername(userRepository.findById(userId).get().getUsername());
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
        user.setUserDetails(null);
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

    public void deleteUser(String id, boolean hasRequest, boolean hasVehicle) throws Exception, EntityNotFoundException {
        try {
            User user = userRepository.findOneById(Long.parseLong(id));

            if(!canDeleteUser(hasRequest, hasVehicle)) {
                throw new Exception("Can't delete user id " + id);
            }

            userRepository.delete(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Id doesn't exists.");
        }
    }

    private boolean canDeleteUser(boolean hasRequest, boolean hasVehicle) {
        return !(hasRequest&&hasVehicle);
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
        Notification notification = new Notification("Failed to update user vehicle number after create.", false);
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

    public List<User> getUnblockedUsers() {
        List<User> users = userRepository.findAll();
        List<User> newList = new ArrayList<>();

        List<UserPrivilege> userPrivileges = userPrivilegeRepository.findAll();
        Set<Long> usersWithPrivilege = new TreeSet<Long>();

        for(UserPrivilege userPrivilege : userPrivileges){
            usersWithPrivilege.add(userPrivilege.getUser().getId());
        }

        for (User user : users) {
            if (usersWithPrivilege.contains(user.getId())) {
                newList.add(user);
            }
        }
        return newList;
    }
}
