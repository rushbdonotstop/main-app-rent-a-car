package com.example.user.service;

import com.example.user.dto.UserPrivilegeDTO;
import com.example.user.dto.UserPrivilegeRequestDTO;
import com.example.user.model.User;
import com.example.user.model.UserPrivilege;
import com.example.user.model.enums.Privilege;
import com.example.user.repository.UserPrivilegeRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPrivilegeService {
    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserPrivilegeDTO> getAllUserPrivileges() {
        List<UserPrivilege> userPrivilegeList = userPrivilegeRepository.findAll();
        List<UserPrivilegeDTO> userPrivilegeDTOList = new ArrayList<UserPrivilegeDTO>();

        for (UserPrivilege userPrivilege: userPrivilegeList
             ) {
            UserPrivilegeDTO userPrivilegeDTOold = null;

            for (UserPrivilegeDTO up: userPrivilegeDTOList
                 ) {
                if(up.getUserId() == userPrivilege.getUser().getId()) {
                    userPrivilegeDTOold = up;
                    break;
                }
            }

            if(userPrivilegeDTOold == null) {
                UserPrivilegeDTO userPrivilegeDTO = new UserPrivilegeDTO();
                userPrivilegeDTO.setUserId(userPrivilege.getUser().getId());
                userPrivilegeDTO.addPrivilege(userPrivilege.getPrivilege().toString());
                userPrivilegeDTOList.add(userPrivilegeDTO);
            } else {
                userPrivilegeDTOold.addPrivilege(userPrivilege.getPrivilege().toString());
            }
        }
        return userPrivilegeDTOList;
    }

    public UserPrivilegeDTO getOneUserPrivileges(String id) throws EntityNotFoundException {
        try {
            userRepository.findOneById(Long.parseLong(id));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User doesn't exists.");
        }

        List<UserPrivilege> userPrivilegeList = userPrivilegeRepository.findAllByUserId(Long.parseLong(id));

        UserPrivilegeDTO userPrivilegeDTO = new UserPrivilegeDTO();
        userPrivilegeDTO.setUserId(Long.parseLong(id));
        userPrivilegeList.stream().forEach(userPrivilege -> {
            userPrivilegeDTO.addPrivilege(userPrivilege.getPrivilege().toString());
        });

        return userPrivilegeDTO;
    }

    public void addPrivilege(String id, UserPrivilegeRequestDTO userPrivilegeRequestDTO) throws EntityNotFoundException, Exception {
        User user;
        try {
            user = userRepository.findOneById(Long.parseLong(id));
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User doesn't exists.");
        }

        UserPrivilegeDTO userPrivilegeDTO = getOneUserPrivileges(id);
        if(userPrivilegeDTO.getUserPrivileges().contains(userPrivilegeRequestDTO.getUserPrivilege())) {
            throw new Exception("User already has " + userPrivilegeRequestDTO.getUserPrivilege() + " privilege.");
        } else {
            UserPrivilege userPrivilege = new UserPrivilege();
            userPrivilege.setUser(user);
            userPrivilege.setPrivilege(Privilege.toEnum(userPrivilegeRequestDTO.getUserPrivilege()));
            userPrivilegeRepository.save(userPrivilege);
        }
    }

    public void deleteUserPrivilege(String id, UserPrivilegeRequestDTO userPrivilegeRequestDTO) throws Exception {
        User user;
        try {
            user = userRepository.findOneById(Long.parseLong(id));
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User doesn't exists.");
        }

        UserPrivilegeDTO userPrivilegeDTO = getOneUserPrivileges(id);
        if(!userPrivilegeDTO.getUserPrivileges().contains(userPrivilegeRequestDTO.getUserPrivilege())) {
            throw new Exception("User doesn't have " + userPrivilegeRequestDTO.getUserPrivilege() + " privilege.");
        } else {
            UserPrivilege userPrivilege = userPrivilegeRepository.findOneByUserIdAndPrivilege(Long.parseLong(id), Privilege.toEnum(userPrivilegeRequestDTO.getUserPrivilege()));
            userPrivilegeRepository.delete(userPrivilege);
        }
    }
}
