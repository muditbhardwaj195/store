package com.example.demo3.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo3.Rowmapper.ShopRowmapper;
import com.example.demo3.models.Shop;

@Repository
public class ShopDao {
	@Autowired
	DataSource datasource;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Shop> getAllShopsDetails() 
	{
		String query = "select * from SHOPS;";
		return jdbcTemplate.query(query, new ShopRowmapper());
	}

	public void addShopByAdmin(Shop shop) {
		String query = "INSERT INTO SHOPS(shopdescription,phone,doornum,streetname,city,state,pincode) VALUES(?,?,?,?,?,?,?)";
		jdbcTemplate.update(query, shop.getShopdescription(),shop.getPhone(),shop.getDoornum(),shop.getStreetname(),shop.getCity(),shop.getState(),shop.getPincode());

		System.out.println("Shop added successfully");
		
	}
}
