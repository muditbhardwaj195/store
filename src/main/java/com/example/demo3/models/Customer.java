package com.example.demo3.models;

import java.sql.Date;

public class Customer {
	private int custid;
	private String name;
	private Date dob;
	private String phone;
	private String gender = "Male";
	private String doornum = "";
	private String streetname = "";
	private String city = "";
	private String state = "";
	private String pincode = "203001";
	private String uname;
	private String email;
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customer [custid=" + custid + ", name=" + name + ", dob=" + dob + ", phone=" + phone + ", gender="
				+ gender + ", doornum=" + doornum + ", streetname=" + streetname + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", uname=" + uname + ", email=" + email + "]";
	}
	public Customer(int custid, String name, Date dob, String phone, String gender, String doornum, String streetname,
			String city, String state, String pincode, String uname, String email) {
		super();
		this.custid = custid;
		this.name = name;
		this.dob = dob;
		this.phone = phone;
		this.gender = gender;
		this.doornum = doornum;
		this.streetname = streetname;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.uname = uname;
		this.email = email;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
}
