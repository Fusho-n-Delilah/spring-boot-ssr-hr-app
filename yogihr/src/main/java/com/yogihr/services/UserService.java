package com.yogihr.services;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.yogihr.dtos.WebUser;
import com.yogihr.models.User;


public interface UserService extends UserDetailsService{
    User findByUsername(String username);

    void save(WebUser webUser);
}
