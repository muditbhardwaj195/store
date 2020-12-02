package com.example.demo3.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo3.models.Admin;

public class AdminRowmapper implements RowMapper<Admin> 
{
    public Admin  mapRow(ResultSet rs, int rn) throws SQLException 
    {
    	Admin admin = new Admin();
    	admin.setAdminid(rs.getInt("adminid"));
    	admin.setCity(rs.getString("city"));
    	admin.setDob(rs.getDate("dob"));
    	admin.setDoornum(rs.getString("doornum"));
    	admin.setEmail(rs.getString("email"));
    	admin.setGender(rs.getString("gender"));
    	admin.setName(rs.getString("name"));
    	admin.setPhone(rs.getString("phone"));
    	admin.setPincode(rs.getString("pincode"));
    	admin.setState(rs.getString("state"));
    	admin.setStreetname(rs.getString("streetname"));
    	admin.setUname(rs.getString("uname"));
        return admin;
        
    }}