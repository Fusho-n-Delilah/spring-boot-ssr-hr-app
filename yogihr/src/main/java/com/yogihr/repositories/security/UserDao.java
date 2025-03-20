package com.yogihr.repositories.security;

import com.yogihr.models.security.User;


public interface UserDao{
    
    User findByUsername(String username);

    void save(User user);
}
