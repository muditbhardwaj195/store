package com.example.demo3.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo3.Rowmapper.ProductRowmapper;
import com.example.demo3.models.Product;

@Repository
public class ProductDao {
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addProductByAdmin(Product product) 
	{
		System.out.println("Product Dao called to add product");
		String query = "INSERT INTO PRODUCT(name,qtyinstock,mrp,description,brand,imname) VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(query, product.getName(), product.getQtyinstock(), product.getMrp(), product.getDescription(),
				product.getBrand(), product.getImname());

		System.out.println("Product added successfully");
	}

	public List<Product> getAllProducts() 
	{
		String query = "select * from PRODUCT where qtyinstock > 0";
		return jdbcTemplate.query(query, new ProductRowmapper());
	}
	
	public Product getSingleProduct(int productid)
	{
		String query = "select * from PRODUCT where id = ?";
		RowMapper<Product> rowmapper = new ProductRowmapper();
		return jdbcTemplate.queryForObject(query, rowmapper, productid);
	}
	
	public void editProductMrpByProductId(Integer productid, float newmrp) 
	{
		String query = "update PRODUCT set mrp = ? where id = ?;";
		jdbcTemplate.update(query,newmrp,productid);
		return;
	}
	
	public void editProductAddQuantityByProductId(Integer productid, Integer addquantity) 
	{
		String sql = "";
		sql = "SELECT qtyinstock FROM PRODUCT WHERE (id = ?)";
		int count = (int)jdbcTemplate.queryForObject(sql, new Object[] { productid}, Integer.class );
		String query = "update PRODUCT set qtyinstock = ? where id = ?;";
		jdbcTemplate.update(query,count + addquantity,productid);
		return;
	}
	
	public void decreaseProductCountByProductId(Integer productid, Integer decreasequantity) 
	{
		String sql = "";
		sql = "SELECT qtyinstock FROM PRODUCT WHERE (id = ?)";
		int count = (int)jdbcTemplate.queryForObject(sql, new Object[] { productid}, Integer.class );
		String query = "update PRODUCT set qtyinstock = ? where id = ?;";
		jdbcTemplate.update(query,count - decreasequantity,productid);
		return;
	}

	public String getProductNameByProductId(int productid) {
		String sql = "Select name from PRODUCT where id = ?;";
		String name = (String)jdbcTemplate.queryForObject(sql, new Object[] { productid}, String.class );
		return name;
	}

	public List<Product> getAllProductsByNameSortedAsc() {
		String query = "select * from PRODUCT where qtyinstock > 0 ORDER BY name ASC;";
		return jdbcTemplate.query(query, new ProductRowmapper());
	}
	
	public List<Product> getAllProductsByNameSortedDesc() {
		String query = "select * from PRODUCT where qtyinstock > 0 ORDER BY name DESC;";
		return jdbcTemplate.query(query, new ProductRowmapper());
	}
	
	public List<Product> getAllProductsByPriceSortedAsc() {
		String query = "select * from PRODUCT where qtyinstock > 0 ORDER BY mrp ASC;";
		return jdbcTemplate.query(query, new ProductRowmapper());
	}
	
	public List<Product> getAllProductsByPriceSortedDesc() {
		String query = "select * from PRODUCT where qtyinstock > 0 ORDER BY mrp DESC;";
		return jdbcTemplate.query(query, new ProductRowmapper());
	}
}
