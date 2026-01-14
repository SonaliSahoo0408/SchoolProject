package com.nimblix.SchoolPEPProject.Security;

import com.nimblix.SchoolPEPProject.Constants.SchoolConstants;
import com.nimblix.SchoolPEPProject.Model.User;
import com.nimblix.SchoolPEPProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String normalized = (username != null && username.contains("@"))
                ? username.toLowerCase()
                : username;

        User user = userRepository
                .findByEmailIdIgnoreCaseOrMobile(normalized, normalized)
                .filter(u -> u.getStatus().equalsIgnoreCase(SchoolConstants.ACTIVE))
                .orElseThrow(() -> new UsernameNotFoundException("Active user not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmailId().toLowerCase(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName()))
        );
    }
}
