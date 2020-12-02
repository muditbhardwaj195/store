package com.example.demo3.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo3.models.Employee;
import com.example.demo3.models.Users;

@Repository
public class UserDao {

	@Autowired
	DataSource datasource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addUser(Users user) {
		System.out.println("Dao called to add user");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode(user.getPassword());
		String sql = "INSERT INTO USERS(username,password,name,email,phone,enabled) VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, user.getUsername(), pwd, user.getName(), user.getEmail(), user.getPhone(), 1);

		sql = "INSERT INTO USERS_ROLES(user,role) VALUES(?,?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getUserRole());
		
		System.out.println("User added successfully");
	}
	
	public void addUserFromEmployee(Employee employee) {
		System.out.println("Dao called to add user");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode(employee.getUpass());
		String sql = "INSERT INTO USERS(username,password,name,email,phone,enabled) VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, employee.getUname(),pwd,employee.getName(),employee.getEmail(),employee.getPhone(), 1);

		sql = "INSERT INTO USERS_ROLES(user,role) VALUES(?,?)";
		jdbcTemplate.update(sql, employee.getUname(),"ROLE_EMPLOYEE");
		
		System.out.println("User added successfully");
	}

	public boolean checkifusernameexists(String uname) {

		String sql = "";
		sql = "SELECT count(username) FROM USERS WHERE (username = ?)";
		int count = (int)jdbcTemplate.queryForObject(sql, new Object[] { uname}, Integer.class );
		System.out.println("No. of Users found : " + count);
		return count > 0 ? true : false;
	}
	
	public boolean checkifusernamewiththisemailexists(String uemail) {

		String sql = "";
		sql = "SELECT count(username) FROM USERS WHERE (email = ?)";
		int count = (int)jdbcTemplate.queryForObject(sql, new Object[] { uemail}, Integer.class );
		System.out.println("No. of Users found : " + count);
		return count > 0 ? true : false;
	}
	
	public void deleteUserWithUsername(String uname)
	{
		if(checkifusernameexists(uname))
		{
			String query = "DELETE FROM USERS WHERE username = ?";
			jdbcTemplate.update(query, new Object[] { uname});
			return;
		}
	}
}
