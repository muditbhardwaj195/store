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

import com.example.demo3.Rowmapper.OrderRowmapper;
import com.example.demo3.Rowmapper.ProductRowmapper;
import com.example.demo3.models.Order;
import com.example.demo3.models.Product;
import com.example.demo3.models.ShowCartProduct;

@Repository
public class OrderDao {

	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertNewOrder(Order order) {
		String sql = "INSERT INTO ORDERS(custid,assignedemployeestatus,paymentmode,totalamount,orderstatus,deliverydoornum,deliverystreetname,deliverycity,deliverystate,deliverypincode,deliveryphone,deliveringto,paymentstatus) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		jdbcTemplate.update(sql, order.getCustid(), order.getAssignedemployeestatus(), order.getPaymentmode(),
				order.getTotalamount(), order.getOrderstatus(), order.getDeliverydoornum(),
				order.getDeliverystreetname(), order.getDeliverycity(), order.getDeliverystate(),
				order.getDeliverypincode(), order.getDeliveryphone(), order.getDeliveringto(),
				order.getPaymentstatus());
		System.out.println("Order added to table");
	}

	public void insertIntoOrderProduct(int orderid, int productid, int quantity) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO ORDERPRODUCTS(orderid,productid,qty) VALUES(?,?,?);";
		jdbcTemplate.update(sql, orderid, productid, quantity);
		return;

	}

	public Order getOrderDetailsByOrderId(int orderid) {
		String sql = "select * from ORDERS where orderid = ?;";
		return jdbcTemplate.queryForObject(sql, new Object[] { orderid }, new OrderRowmapper());
	}

	public List<Order> getAllOrdersByCustid(int customerid) {
		String query = "select * from ORDERS where custid = ? order by orderid desc;";
		return jdbcTemplate.query(query, new Object[] { customerid }, new OrderRowmapper());
	}

	public List<ShowCartProduct> getorderproductdetails(int orderid) {

		String sql = "select * from ORDERPRODUCTS where orderid = ?;";
		return jdbcTemplate.query(sql, new Object[] { orderid }, new ResultSetExtractor<List<ShowCartProduct>>() {

			public List<ShowCartProduct> extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<ShowCartProduct> showcartlist = new ArrayList<ShowCartProduct>();

				while (rs.next()) {
					
					ShowCartProduct showcartproduct = new ShowCartProduct();
					showcartproduct.setProductid(rs.getInt("productid"));
					showcartproduct.setQuantity(rs.getInt("qty"));
					
					showcartlist.add(showcartproduct);

				}
				return showcartlist;
			}

		});

	}

	public void updateorderstatus(int orderid, String orderstatus) 
	{
		String sql = "update ORDERS set orderstatus = ? where orderid = ?;";
		jdbcTemplate.update(sql,orderstatus,orderid);
		return;
	}
	
	public List<Order> getAllOrdersForAdmin() 
	{
		String query = "select * from ORDERS order by orderid desc";
		return jdbcTemplate.query(query, new Object[] { }, new OrderRowmapper());
	}

	public void assignEmployeeByOrderId(int employeeid, int orderid) {
		String sql = "update ORDERS set assignedemployeeid = ? where orderid = ?;";
		jdbcTemplate.update(sql,employeeid,orderid);
		sql = "update ORDERS set assignedemployeestatus = ? where orderid = ?;";
		jdbcTemplate.update(sql,"ASSIGNED",orderid);
		return;
	}
	
	public int tellAdminUnassignedOrder()
	{
		String sql = "";
		sql = "SELECT count(orderid) FROM ORDERS WHERE (orderstatus = ?) AND (assignedemployeestatus = ?);";
		int count = (int)jdbcTemplate.queryForObject(sql, new Object[] { "PENDING", "PENDING"}, Integer.class );
		return count;
	}

	public List<Order> getOrderListForEmployeeByEmployeeID(int employeeid) 
	{
		String query = "select * from ORDERS where assignedemployeeid = ? and orderstatus = ? order by orderid desc";
		return jdbcTemplate.query(query, new Object[] {employeeid,"PENDING" }, new OrderRowmapper());
	}

	public int tellEmployeeTotalUndeliveredAssignedOrder(int employeeid) 
	{
		String sql = "";
		sql = "SELECT count(orderid) FROM ORDERS WHERE (orderstatus = ?) AND (assignedemployeeid = ?);";
		int count = (int)jdbcTemplate.queryForObject(sql, new Object[] { "PENDING", employeeid}, Integer.class );
		return count;
	}

	public void markOrderDeliveredByOrderId(int orderid) 
	{
		String sql = "update ORDERS set orderstatus = ?, paymentstatus = ? where orderid = ?;";
		jdbcTemplate.update(sql,"DELIVERED","PAID",orderid);
		return;
	}

	public List<Order> getAllAssignedOrderListForEmployeeByEmployeeID(int employeeid) {
		String query = "select * from ORDERS where assignedemployeeid = ? order by orderid desc";
		return jdbcTemplate.query(query, new Object[] {employeeid }, new OrderRowmapper());
	}

}
