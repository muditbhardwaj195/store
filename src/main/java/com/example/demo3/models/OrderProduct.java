package com.example.demo3.models;

public class OrderProduct {
	private int orderid;
	private int productid;
	private int qty;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public OrderProduct(int orderid, int productid, int qty) {
		super();
		this.orderid = orderid;
		this.productid = productid;
		this.qty = qty;
	}
	public OrderProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderProduct [orderid=" + orderid + ", productid=" + productid + ", qty=" + qty + "]";
	}
}
