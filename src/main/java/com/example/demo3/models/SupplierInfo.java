package com.example.demo3.models;

import java.sql.Date;

public class SupplierInfo 
{
	private int supplierid;
	private String name;
	private String phone;
	private String doornum;
	private String streetname;
	private String city;
	private String state;
	private String pincode;
	private String email;
	private Date dob;
	public int getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDoornum() {
		return doornum;
	}
	public void setDoornum(String doornum) {
		this.doornum = doornum;
	}
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public SupplierInfo(int supplierid, String name, String phone, String doornum, String streetname, String city,
			String state, String pincode, String email, Date dob) {
		super();
		this.supplierid = supplierid;
		this.name = name;
		this.phone = phone;
		this.doornum = doornum;
		this.streetname = streetname;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.email = email;
		this.dob = dob;
	}
	public SupplierInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SupplierInfo [supplierid=" + supplierid + ", name=" + name + ", phone=" + phone + ", doornum=" + doornum
				+ ", streetname=" + streetname + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", email=" + email + ", dob=" + dob + "]";
	}
}
