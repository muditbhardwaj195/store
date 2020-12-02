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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo3.Rowmapper.ProductRowmapper;
import com.example.demo3.Rowmapper.SupplierInfoRowmapper;
import com.example.demo3.models.Product;
import com.example.demo3.models.ShowCartProduct;
import com.example.demo3.models.SupplierInfo;

@Repository
public class SupplierDao 
{
	@Autowired
	DataSource datasource;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addSupplierByAdmin(SupplierInfo supplier) 
	{
		System.out.println("Supplier Dao called to add supplier");
		String query = "INSERT INTO SUPPLIERINFO(name,phone,dob,doornum,streetname,city,state,pincode,email) VALUES(?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(query, supplier.getName(),supplier.getPhone(),supplier.getDob(),supplier.getDoornum(),supplier.getStreetname(),supplier.getCity(),supplier.getState(),supplier.getPincode(),supplier.getEmail());

		System.out.println("Supplier added successfully");
	}

	public int getlatestsupplieridadded() {
		String sql = "Select max(supplierid) from SUPPLIERINFO;";
		Integer supplierid = (Integer)jdbcTemplate.queryForObject(sql, new Object[] { }, Integer.class );
		return supplierid;
	}

	public List<SupplierInfo> getAllSuppliersForAdmin() {
		String query = "select * from SUPPLIERINFO;";
		return jdbcTemplate.query(query, new SupplierInfoRowmapper());
	}

	public SupplierInfo getSupplierDetailById(int supplierid) {
		String query = "select * from SUPPLIERINFO where supplierid = ?";
		RowMapper<SupplierInfo> rowmapper = new SupplierInfoRowmapper();
		return jdbcTemplate.queryForObject(query, rowmapper, supplierid);
	}

	public List<Integer> getAllProductsOfSupplierByID(int supplierid) {
		String sql = "select productid from SUPPLY where supplierid = ?;";
		return jdbcTemplate.query(sql, new Object[] { supplierid }, new ResultSetExtractor<List<Integer>>() {

			public List<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<Integer> productlist = new ArrayList<Integer>();

				while (rs.next()) {
					
					Integer tmpid = rs.getInt("productid");
					
					productlist.add(tmpid);

				}
				return productlist;
			}

		});
	}

	public void addProductToSupplier(int supplierid, int productid) {
		String query = "INSERT INTO SUPPLY(supplierid,productid) VALUES(?,?);";
		jdbcTemplate.update(query, supplierid,productid);
	}

	public void RemoveProductFromSupplier(Integer supplierid, Integer productid) {
		String query = "delete from SUPPLY where supplierid = ? and productid = ?;";
		jdbcTemplate.update(query, supplierid,productid);
		
	}
}
