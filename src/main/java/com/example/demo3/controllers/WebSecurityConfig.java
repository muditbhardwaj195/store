package com.example.demo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired	
	UserDetailsService userDetailsService;
	@Autowired
    AuthSuccessHandler customSuccessHandler;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception 
	{			 
	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());;
		
	}	
	
 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("reached web security config");

	  http.authorizeRequests()
		.antMatchers("/admin**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/customer**").access("hasRole('ROLE_CUSTOMER')")
		.antMatchers("/employee**").access("hasRole('ROLE_EMPLOYEE')")
		// .antMatchers("/employee*").access("hasRole('ROLE_EMPLOYEE')")
		.anyRequest().permitAll()
		.and()
		  .formLogin().loginPage("/login")	
		  .successHandler(customSuccessHandler)
		  .usernameParameter("username").passwordParameter("password")		  
		.and()
		  .logout().logoutRequestMatcher(new AntPathRequestMatcher("/j_spring_security_logout")).logoutSuccessUrl("/logout")	
		  .deleteCookies("JSESSIONID")
		  .clearAuthentication(true)
        	.invalidateHttpSession(true)
		 .and()
		 .exceptionHandling().accessDeniedPage("/register")
		.and()
		  .csrf().disable();
	}

	

	
	@Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
    	return new BCryptPasswordEncoder();
    }
}