package com.example.user.service;

import com.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserDetailsTokenService {
    @Autowired
    UserPrivilegeService userPrivilegeService;

    public UserDetails getUserDetails(User user) {
        try {
            UserDetails userDetails = new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    String authorities = "ROLE_" + user.getUserDetails().getUserType().toString();
                    for (String authority :
                            userPrivilegeService.getOneUserPrivileges(user.getId().toString()).getUserPrivileges()) {
                        authorities += ", " + authority;
                    }
                    return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getUsername();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    if (userPrivilegeService.getOneUserPrivileges(user.getId().toString()).getUserPrivileges().size() == 0) {
                        return false;
                    }
                    return true;
                }
            };

            return userDetails;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Bad credentials");
        }
    }
}
