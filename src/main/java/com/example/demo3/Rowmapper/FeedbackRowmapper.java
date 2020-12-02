package com.example.demo3.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo3.models.Feedback;

public class FeedbackRowmapper implements RowMapper<Feedback> 
{
    public Feedback  mapRow(ResultSet rs, int rn) throws SQLException 
    {
    	
    	Feedback feedback = new Feedback();
    	feedback.setCustomerid(rs.getInt("custid"));
    	feedback.setEmployeerating(rs.getInt("employeerating"));
    	feedback.setFeedbackid(rs.getInt("feedbackid"));
    	feedback.setFeedbacktext(rs.getString("feedbacktext"));
    	feedback.setOrderid(rs.getInt("orderid"));
    	feedback.setWebsiterating(rs.getInt("websiterating"));
    	return feedback;
    }}