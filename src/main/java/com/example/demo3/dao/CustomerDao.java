package com.example.demo3.dao;

import javax.sql.DataSource;
import javax.validation.Valid;

import com.example.demo3.Rowmapper.ShowCartProductRowmapper;
import com.example.demo3.models.ShowCartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo3.Rowmapper.CustomerRowmapper;
import com.example.demo3.models.Customer;
import com.example.demo3.models.Feedback;
import com.example.demo3.models.Users;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao {
	@Autowired
	DataSource datasource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addCustomerFromUser(Users user) {
		System.out.println("CustomerDao called to add customer");
		String sql = "INSERT INTO CUSTOMER(name,phone,uname,email) VALUES(?,?,?,?)";
		jdbcTemplate.update(sql, user.getName(), user.getPhone(), user.getUsername(), user.getEmail());

		System.out.println("Customer added successfully");
	}

	public Customer getUserByUsername(String username) {
		String sql = "select * from CUSTOMER where uname='" + username + "'";
		return jdbcTemplate.queryForObject(sql, new CustomerRowmapper());
	}

	public List<ShowCartProduct> getShowCartProductsByUserid(int customerid) {
		String query = "select p.id as pid, p.name as pname,p.description as pdescription,p.brand as pbrand,p.mrp as pmrp,p.imname as pimname,c.qty as cquantity,p.qtyinstock as pqtyinstock from PRODUCT as p, CART as c where c.custid ="
				+ Integer.toString(customerid) + " and c.productid = p.id;";
		List<ShowCartProduct> temp = new ArrayList<ShowCartProduct>();
		temp.addAll(jdbcTemplate.query(query, new ShowCartProductRowmapper()));
		System.out.println(temp);
//		String sql = "select p.imid as imid, p.id as pid,p.name as pname,c.qty as cqty,p.mrp as pmrp from PRODUCT as p,CART as c where (c.custid="+Integer.toString(custid)+" and c.productid=p.id)";
//		List<PC> qw=new ArrayList<PC>();
//		qw.addAll( jdbcTemplate.query(sql,new PCRowMapper()));
//		List<Showcart> showcartlist = new ArrayList<Showcart>();
//		for(PC rs:qw){
//			Showcart showcart = new Showcart(rs.getPid(),rs.getPname(),rs.getCqty(), rs.getPmrp() );
//			showcartlist.add(showcart);
//		}
		return temp;
	}

	public boolean checkIfProductExistInCartOfCustomer(int customerid, int productid) {
		String sql = "select count(*) from CART where custid = ? AND productid = ?;";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { customerid, productid }, Integer.class);
		return count > 0 ? true : false;
	}

	public void deleteSingleProductFromCart(int customerid, int productid) {
		System.out.println("Reached Custumer Dao to delete one product quantity from cart of userid : " + customerid);
		if (checkIfProductExistInCartOfCustomer(customerid, productid)) {
			String query = "SELECT qty FROM CART WHERE custid = ? AND productid = ?;";
			int count = (int) jdbcTemplate.queryForObject(query, new Object[] { customerid, productid }, Integer.class);
			if (count > 1) {
				query = "UPDATE CART set qty = ? WHERE custid = ? AND productid = ?";
				jdbcTemplate.update(query, count - 1, customerid, productid);
				return;
			} else {
				query = "delete from CART where custid=? and productid=?";
				jdbcTemplate.update(query, new Object[] { customerid, productid });
				return;
			}
		}
	}

	public void addSingleProductInCart(int customerid, int productid, int counter) {
		System.out.println("Reached Custumer Dao to add one product quantity in cart of userid : " + customerid);
		if (checkIfProductExistInCartOfCustomer(customerid, productid)) {
			String query = "SELECT qty FROM CART WHERE custid = ? AND productid = ?;";
			int count = (int) jdbcTemplate.queryForObject(query, new Object[] { customerid, productid }, Integer.class);
			query = "UPDATE CART set qty = ? WHERE custid = ? AND productid = ?";
			jdbcTemplate.update(query, count + counter, customerid, productid);
			return;
		} else {
			String query = "INSERT INTO CART VALUES(?,?,?)";
			jdbcTemplate.update(query, customerid, productid, counter);
			return;
		}
	}

	public void deleteWholeProductFromCartOfUser(int customerid, int productid) {
		System.out.println("Reached Custumer Dao to delete whole product from cart of userid : " + customerid);
		if (checkIfProductExistInCartOfCustomer(customerid, productid)) {
			String query = "delete from CART where custid=? and productid=?";
			jdbcTemplate.update(query, new Object[] { customerid, productid });
			return;
		}
	}

	public void updateCustomerInfo(Customer customer) {
		System.out.println("haha " + customer.toString());
		String sql = "update CUSTOMER set phone=?, gender=?, doornum=?, streetname=?, city=?, state=?, pincode=?, dob = ? where uname=?;";
		jdbcTemplate.update(sql, customer.getPhone(), customer.getGender(), customer.getDoornum(),
				customer.getStreetname(), customer.getCity(), customer.getState(), customer.getPincode(),customer.getDob(),
				customer.getUname());
	}

	public List<Customer> getAllCustomerDetails() {
		String query = "SELECT * FROM CUSTOMER;";
		List<Customer> temp = new ArrayList<Customer>();
		temp.addAll(jdbcTemplate.query(query, new CustomerRowmapper()));
		return temp;
	}

	public int getlastorderbycustid(int custid) {

		String sql = "SELECT max(orderid) as maxorderid FROM ORDERS WHERE custid=?";
		return jdbcTemplate.query(sql, new Object[] { custid }, new ResultSetExtractor<Integer>() {

			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {

					int orderid = rs.getInt("maxorderid");

					return orderid;
				}
				return null;

			}

		});

	}

	public Customer getUserByUserId(Integer customerid) {
		String sql = "select * from CUSTOMER where custid = ?;";
		return jdbcTemplate.queryForObject(sql,new Object[] { customerid}, new CustomerRowmapper());
	}
	
	public int tellCustomerTotalProductInCart(int custid) 
	{
		String sql = "";
		sql = "SELECT count(productid) FROM CART WHERE (custid = ?);";
		int count = (int)jdbcTemplate.queryForObject(sql, new Object[] {custid}, Integer.class );
		return count;
	}

	public void addFeedback(Feedback feedback) {
		String sql = "INSERT INTO FEEDBACK(websiterating,employeerating,feedbacktext,orderid,custid) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql, feedback.getWebsiterating(),feedback.getEmployeerating(),feedback.getFeedbacktext(),feedback.getOrderid(),feedback.getCustomerid());

		System.out.println("Feedback added successfully");
	}
}
