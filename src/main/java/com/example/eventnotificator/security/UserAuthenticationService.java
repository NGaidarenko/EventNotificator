package com.example.eventnotificator.security;

import com.example.eventnotificator.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    public User getCurrentAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Authentication not present");
        }
        return (User) authentication.getPrincipal();
    }

//    public String getRoleCurrentAuthenticated() {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            throw new IllegalStateException("Authentication not present");
//        }
//        return (String) authentication.getAuthorities();
//    }
}
