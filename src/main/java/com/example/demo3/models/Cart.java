package com.example.demo3.models;

public class Cart {
	private int customerid;
	private int productid;
	private int quantity = 0;
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Cart(int customerid, int productid, int quantity) {
		super();
		this.customerid = customerid;
		this.productid = productid;
		this.quantity = quantity;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Cart [customerid=" + customerid + ", productid=" + productid + ", quantity=" + quantity + "]";
	}
}
