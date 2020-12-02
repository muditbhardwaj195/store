package com.example.demo3.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo3.models.Shop;

public class ShopRowmapper implements RowMapper<Shop> 
{
    public Shop  mapRow(ResultSet rs, int rn) throws SQLException 
    {
    	
    	Shop shop = new Shop();
    	shop.setCity(rs.getString("city"));
    	shop.setDoornum(rs.getString("doornum"));
    	shop.setPhone(rs.getString("phone"));
    	shop.setPincode(rs.getString("pincode"));
    	shop.setShopdescription(rs.getString("shopdescription"));
    	shop.setShopid(rs.getInt("shopid"));
    	shop.setState(rs.getString("state"));;
    	shop.setStreetname(rs.getString("streetname"));
    	return shop;
    }}