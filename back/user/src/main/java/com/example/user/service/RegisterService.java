package com.example.user.service;

import com.example.user.dto.EmailDTO;
import com.example.user.model.User;
import com.example.user.model.UserDetails;
import com.example.user.model.enums.UserType;
import com.example.user.repository.UserDetailsRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        newUser.setPassword(user.getPassword());
        newUser.setUserDetails(udFromBase);
        newUser.setVerified(false);
        newUser = userRepository.save(user);

        EmailDTO email = new EmailDTO();
        email.setEmailTo(udFromBase.getEmail());
        email.setSubject("Welcome to Rento!");
        email.setBody(verificationTokenService.generateVerificationToken(newUser));

        return email;
    }

    public boolean validate(User user) {
        boolean match1 = false;
        boolean match2 = false;
        boolean match3 = false;

        Pattern emailRegex = Pattern.compile("[^@]+@[^\\.]+\\..+", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailRegex.matcher(user.getUserDetails().getEmail());


        Pattern passwordRegex = Pattern.compile("(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}");
        Matcher passwordMatcher = passwordRegex.matcher(user.getPassword());

        Pattern usernameRegex = Pattern.compile("[a-zA-Z0-9]+");
        Matcher usernameMatcher = usernameRegex.matcher(user.getUsername());

        if (emailMatcher.find()) {
            match1 = true;
        }
        if (passwordMatcher.find()) {
            match2 = true;
        }
        if (usernameMatcher.find()) {
            match3 = true;
        }

        if (match1 && match2 && match3) {
            return true;
        } else {
            return false;
        }
    }
}
