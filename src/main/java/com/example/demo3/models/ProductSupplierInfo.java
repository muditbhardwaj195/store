package com.example.demo3.models;

public class ProductSupplierInfo 
{
	private int supplierid;
	private int productid;
	public int getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public ProductSupplierInfo(int supplierid, int productid) {
		super();
		this.supplierid = supplierid;
		this.productid = productid;
	}
	public ProductSupplierInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProductSupplierInfo [supplierid=" + supplierid + ", productid=" + productid + "]";
	}
}
