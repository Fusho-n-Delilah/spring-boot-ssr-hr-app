package com.yogihr.repositories;

import org.springframework.stereotype.Repository;

import com.yogihr.models.User;


public interface UserDao{
    
    User findByUsername(String username);

    void save(User user);
}
