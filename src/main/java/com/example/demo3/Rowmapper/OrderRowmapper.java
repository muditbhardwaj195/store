package com.example.demo3.Rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo3.models.Order;

public class OrderRowmapper implements RowMapper<Order> 
{
    public Order  mapRow(ResultSet rs, int rn) throws SQLException 
    {
    	
    	Order order = new Order();
    	order.setAssignedemployeestatus(rs.getString("assignedemployeestatus"));
    	order.setAssignedemployeeid(rs.getInt("assignedemployeeid"));
    	order.setCustid(rs.getInt("custid"));
    	order.setDeliveringto(rs.getString("deliveringto"));
    	order.setDeliverycity(rs.getString("deliverycity"));
    	order.setDeliverydoornum(rs.getString("deliverydoornum"));
    	order.setDeliveryphone(rs.getString("deliveryphone"));
    	order.setDeliverypincode(rs.getString("deliverypincode"));
    	order.setDeliverystate(rs.getString("deliverystate"));
    	order.setDeliverystreetname(rs.getString("deliverystreetname"));
    	order.setOrderdatetime(rs.getDate("orderdatetime"));
    	order.setOrderid(rs.getInt("orderid"));
    	order.setOrderstatus(rs.getString("orderstatus"));
    	order.setPaymentmode(rs.getString("paymentmode"));
    	order.setPaymentstatus(rs.getString("paymentstatus"));
    	order.setTotalamount(rs.getDouble("totalamount"));
    	return order;
    }
}