package com.yogihr.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.yogihr.models.User;

import com.yogihr.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService){
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        String username = authentication.getName();

        User user = userService.findByUsername(username);

        HttpSession session = request.getSession();

        session.setAttribute("user", user);

        response.sendRedirect(request.getContextPath() +  "/home");
    }
    
}
