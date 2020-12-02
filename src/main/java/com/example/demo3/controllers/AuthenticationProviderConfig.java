package com.example.demo3.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
public class AuthenticationProviderConfig {
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() 
	{
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test6?useSSL=false");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("root");
	    return driverManagerDataSource;	
	}
    
    @Bean(name="userDetailsService")
	public UserDetailsService userDetailsService()
	{
    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    	jdbcImpl.setDataSource(dataSource());
    	System.out.println("HEY");
    	jdbcImpl.setUsersByUsernameQuery("select username,password,enabled from USERS where username=?");
    	jdbcImpl.setAuthoritiesByUsernameQuery("select user,role from USERS_ROLES where user=?");
    	return jdbcImpl;
    }
}