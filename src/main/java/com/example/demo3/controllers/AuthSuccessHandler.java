package com.example.demo3.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        // Get the role of logged in user
    	System.out.println("&&&");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        System.out.println("Reached AuthSuccessHandler");

        String targetUrl = "/loginfailed";
        if(role.contains("ROLE_CUSTOMER")) {
            targetUrl = "/customer";
        }  
        if(role.contains("ROLE_ADMIN")) {
            targetUrl = "/admin";
        }
        if(role.contains("ROLE_EMPLOYEE")) {
            targetUrl = "/employee";
        }
        return targetUrl;
    }
}