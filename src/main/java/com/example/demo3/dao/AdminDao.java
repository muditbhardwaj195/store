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

import com.example.demo3.Rowmapper.AdminRowmapper;
import com.example.demo3.Rowmapper.FeedbackRowmapper;
import com.example.demo3.models.Admin;
import com.example.demo3.models.Feedback;
import com.example.demo3.models.Users;

@Repository
public class AdminDao {
	@Autowired
	DataSource datasource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addAdminFromUser(Users user) {
		System.out.println("Admin Dao called to add new admin");
		String sql = "INSERT INTO ADMININFO(name,phone,uname,email) VALUES(?,?,?,?)";
		jdbcTemplate.update(sql, user.getName(), user.getPhone(), user.getUsername(), user.getEmail());

		System.out.println("Admin added successfully");
	}

	public Admin getAdminDetailsByUsername(String username) {
		String sql = "SELECT * FROM ADMININFO WHERE uname=?;";
		return jdbcTemplate.query(sql, new Object[] { username }, new ResultSetExtractor<Admin>() {

			public Admin extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {

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
				}
				return null;

			}

		});
	}

	public void updateAdminDetails(Admin admin) {
		String sql = "update ADMININFO set dob=?, phone=?, gender=?, doornum=?, streetname=?, city=?, state=?, pincode=? where uname=?";
		jdbcTemplate.update(sql, admin.getDob(), admin.getPhone(), admin.getGender(),admin.getDoornum(), admin.getStreetname(), admin.getCity(), admin.getState(),
				admin.getPincode(), admin.getUname());
	}
	
	public List<Admin> getAllAdminDetails() {
		String query = "SELECT * FROM ADMININFO;";
		List<Admin> temp = new ArrayList<Admin>();
		temp.addAll(jdbcTemplate.query(query, new AdminRowmapper()));
		System.out.println(temp);
		return temp;
	}

	public List<Feedback> getAllFeedbackForAdmin() {
		String query = "SELECT * FROM FEEDBACK;";
		List<Feedback> temp = new ArrayList<Feedback>();
		temp.addAll(jdbcTemplate.query(query, new FeedbackRowmapper()));
		return temp;
	}
}
