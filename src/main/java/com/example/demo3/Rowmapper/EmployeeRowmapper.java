package com.example.demo3.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo3.models.Employee;

public class EmployeeRowmapper implements RowMapper<Employee> 
{
    public Employee  mapRow(ResultSet rs, int rn) throws SQLException 
    {
    	Employee employee = new Employee();
    	employee.setCity(rs.getString("city"));
    	employee.setDob(rs.getDate("dob"));
    	employee.setDoornum(rs.getString("doornum"));
    	employee.setEmail(rs.getString("email"));
    	employee.setEmployeeid(rs.getInt("employeeid"));
    	employee.setGender(rs.getString("gender"));
    	employee.setHiringdate(rs.getDate("hiringdate"));
    	employee.setName(rs.getString("name"));
    	employee.setPhone(rs.getString("phone"));
    	employee.setPincode(rs.getString("pincode"));
    	employee.setSalary(rs.getDouble("salary"));
    	employee.setShopid(rs.getInt("shopid"));
    	employee.setState(rs.getString("state"));
    	employee.setStreetname(rs.getString("streetname"));
    	employee.setUname(rs.getString("uname"));
    	return employee;
    }
}