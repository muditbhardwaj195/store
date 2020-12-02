package com.example.demo3.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo3.models.Product;

public class ProductRowmapper implements RowMapper<Product> 
{
    public Product  mapRow(ResultSet rs, int rn) throws SQLException 
    {

        Product product = new Product();
        product.setBrand(rs.getString("brand"));
        product.setDescription(rs.getString("description"));
        product.setId(rs.getInt("id"));
        product.setMrp(rs.getDouble("mrp"));
        product.setName(rs.getString("name"));
        product.setQtyinstock(rs.getInt("qtyinstock"));
        product.setImname(rs.getString("imname"));

        return product;
        
    }}