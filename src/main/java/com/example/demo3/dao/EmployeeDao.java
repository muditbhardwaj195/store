package com.example.demo3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.demo3.Rowmapper.EmployeeRowmapper;
import com.example.demo3.models.Employee;

@Repository
public class EmployeeDao {
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addEmployee(Employee employee) {
		System.out.println("Dao called to add employee");
		String sql = "INSERT INTO EMPLOYEE(uname,name,email,phone,salary,gender,shopid,hiringdate,dob) VALUES(?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, employee.getUname(),employee.getName(),employee.getEmail(),employee.getPhone(),employee.getSalary(),employee.getGender(),employee.getShopid(),employee.getHiringdate(),employee.getDob());
		
		System.out.println("Employee added successfully");
	}
	
	public List<Employee> getAllEmployeeDetails() {
		String query = "SELECT * FROM EMPLOYEE;";
		List<Employee> temp = new ArrayList<Employee>();
		temp.addAll(jdbcTemplate.query(query, new EmployeeRowmapper()));
		System.out.println(temp);
		return temp;
	}
	
	public Employee getEmployeeDetailsByUsername(String username) {
		String sql = "SELECT * FROM EMPLOYEE WHERE uname=?;";
		return jdbcTemplate.query(sql, new Object[] { username }, new ResultSetExtractor<Employee>() {

			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					
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
				return null;

			}

		});
	}

	public void setSalaryForEmployeeByUsername(float newsalary,String uname) {
		String query = "update EMPLOYEE set salary = ? where uname = ?;";
		jdbcTemplate.update(query,newsalary,uname);
		return;
	}
	
	public void setShopidForEmployeeByUsername(int shopid,String uname) {
		String query = "update EMPLOYEE set shopid = ? where uname = ?;";
		jdbcTemplate.update(query,shopid,uname);
		return;
	}

	public Employee getEmployeeDetailsByID(int employeeid) {
		String sql = "SELECT * FROM EMPLOYEE WHERE employeeid=?;";
		return jdbcTemplate.query(sql, new Object[] { employeeid }, new ResultSetExtractor<Employee>() {

			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					
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
				return null;

			}

		});
	}

	public void updateEmployeeDetails(Employee employee) {
		String query = "update EMPLOYEE set name = ? , phone = ? , gender = ? ,doornum = ? , streetname = ? , city = ? , state = ? , pincode = ? , dob = ? where uname = ?;";
		jdbcTemplate.update(query,employee.getName(),employee.getPhone(),employee.getGender(),employee.getDoornum(),employee.getStreetname(),employee.getCity(),employee.getState(),employee.getPincode(),employee.getDob(),employee.getUname());
	}
	
}
