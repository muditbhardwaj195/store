package com.example.demo3.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo3.models.Customer;

public class CustomerRowmapper implements RowMapper<Customer> 
{
    public Customer  mapRow(ResultSet rs, int rn) throws SQLException 
    {

        Customer customer = new Customer();
        customer.setCity(rs.getString("city"));
        customer.setCustid(rs.getInt("custid"));
        customer.setDob(rs.getDate("dob"));
        customer.setDoornum(rs.getString("doornum"));
        customer.setEmail(rs.getString("email"));
        customer.setGender(rs.getString("gender"));
        customer.setName(rs.getString("name"));
        customer.setPhone(rs.getString("phone"));
        customer.setPincode(rs.getString("pincode"));
        customer.setState(rs.getString("state"));
        customer.setStreetname(rs.getString("streetname"));
        customer.setUname(rs.getString("uname"));
        return customer;
        
    }
}