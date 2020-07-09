package com.example.user.service;

import com.example.user.dto.EmailDTO;
import com.example.user.dto.UserPrivilegeRequestDTO;
import com.example.user.model.AgentRequest;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
import com.example.user.model.enums.UserType;
import com.example.user.repository.AgentRequestRepository;
import com.example.user.repository.UserDetailsRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    AgentRequestRepository agentRequestRepository;
    
    @Autowired
    UserPrivilegeService userPrivilegeService;

    public EmailDTO registerUser(User user) throws Exception {
        UserDetails newUserDetails = user.getUserDetails();
        newUserDetails.setVehicleNum(0);
        UserDetails udFromBase = userDetailsRepository.save(newUserDetails);

        String salt = BCrypt.gensalt(12);
        String saltedPassword = BCrypt.hashpw(user.getPassword(), salt);

        User newUser = user;
        newUser.setSalt(salt);
        newUser.setPassword(saltedPassword);
        newUser.setUserDetails(udFromBase);
        newUser.setVerified(false);
        newUser = userRepository.save(newUser);

        userPrivilegeService.addPrivilege(newUser.getId().toString(), new UserPrivilegeRequestDTO("RENT_VEHICLE"));
        userPrivilegeService.addPrivilege(newUser.getId().toString(), new UserPrivilegeRequestDTO("ADD_VEHICLE"));

        EmailDTO email = new EmailDTO();
        email.setEmailTo(udFromBase.getEmail());
        email.setSubject("Welcome to Rento!");
        if(newUserDetails.getUserType()==UserType.END_USER) {
            email.setBody(verificationTokenService.generateVerificationToken(newUser));
        } else {
            AgentRequest agentRequest = new AgentRequest();
            agentRequest.setUserId(newUser.getId());
            agentRequestRepository.save(agentRequest);

            udFromBase.setUserType(UserType.END_USER);
            email.setBody(verificationTokenService.generateVerificationToken(newUser) + "\n Registered as END USER. For agent privileges" +
                    "wait until administrator approves registration request.");
        }


        return email;
    }

    public List<AgentRequest> getAllAgentRequests() {
        return agentRequestRepository.findAll();
    }

    public void deleteAgentRequest(Long id) {
        agentRequestRepository.deleteById(id);
    }

    public void approveAgent(Long agentRequestId) throws Exception {
        AgentRequest agentRequest = agentRequestRepository.findOneById(agentRequestId);
        User user = userRepository.findOneById(agentRequest.getUserId());
        user.getUserDetails().setUserType(UserType.AGENT);

        userPrivilegeService.addPrivilege(user.getId().toString(), new UserPrivilegeRequestDTO("ADD_DISCOUNT"));
        userPrivilegeService.addPrivilege(user.getId().toString(), new UserPrivilegeRequestDTO("GET_STATISTIC"));
        
        userRepository.save(user);
        agentRequestRepository.delete(agentRequest);

    }
}
