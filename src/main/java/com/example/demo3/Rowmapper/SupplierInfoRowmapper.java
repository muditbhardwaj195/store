package com.example.demo3.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo3.models.SupplierInfo;

public class SupplierInfoRowmapper implements RowMapper<SupplierInfo> 
{
    public SupplierInfo  mapRow(ResultSet rs, int rn) throws SQLException 
    {
    	
    	SupplierInfo supplier = new SupplierInfo();
    	supplier.setCity(rs.getString("city"));
    	supplier.setDob(rs.getDate("dob"));
    	supplier.setDoornum(rs.getString("doornum"));
    	supplier.setEmail(rs.getString("email"));
    	supplier.setName(rs.getString("name"));
    	supplier.setPhone(rs.getString("phone"));
    	supplier.setPincode(rs.getString("pincode"));
    	supplier.setState(rs.getString("state"));
    	supplier.setStreetname(rs.getString("streetname"));
    	supplier.setSupplierid(rs.getInt("supplierid"));
    	
    	return supplier;
        
    }
}