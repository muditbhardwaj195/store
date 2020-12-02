package com.example.demo3.models;

public class Product {
	private int id;
	private int qtyinstock;
	private String name;
	private String description;
	private String brand;
	private double mrp;
	private String imname = "/images/product_01.png";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQtyinstock() {
		return qtyinstock;
	}
	public void setQtyinstock(int qtyinstock) {
		this.qtyinstock = qtyinstock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public String getImname() {
		return imname;
	}
	public void setImname(String imname) {
		this.imname = imname;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", qtyinstock=" + qtyinstock + ", name=" + name + ", description=" + description
				+ ", brand=" + brand + ", mrp=" + mrp + ", imname=" + imname + "]";
	}
	public Product(int id, int qtyinstock, String name, String description, String brand, double mrp, String imname) {
		super();
		this.id = id;
		this.qtyinstock = qtyinstock;
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.mrp = mrp;
		this.imname = imname;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
}
