package com.example.user.service;

import com.example.user.dto.EmailDTO;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
import com.example.user.model.enums.UserType;
import com.example.user.repository.UserDetailsRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    VerificationTokenService verificationTokenService;

    public EmailDTO registerUser(User user) {
        UserDetails newUserDetails = user.getUserDetails();
        newUserDetails.setUserType(UserType.END_USER);
        newUserDetails.setVehicleNum(0);
        UserDetails udFromBase = userDetailsRepository.save(newUserDetails);

        User newUser = user;
        newUser.setUserDetails(udFromBase);
        newUser.setVerified(false);
        newUser = userRepository.save(user);

        EmailDTO email = new EmailDTO();
        email.setEmailTo(udFromBase.getEmail());
        email.setSubject("Welcome to Rento!");
        email.setBody(verificationTokenService.generateVerificationToken(newUser));

        return email;
    }
}
