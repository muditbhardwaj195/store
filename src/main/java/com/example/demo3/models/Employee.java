package com.example.demo3.models;

import java.sql.Date;

public class Employee {
	private int employeeid;
	private String name;
	private String phone;
	private String doornum;
	private String streetname;
	private String city;
	private String state;
	private String pincode;
	private String uname;
	private String upass;
	private int shopid;
	private double salary;
	private Date hiringdate;
	private Date dob;
	private String gender;
	private String email;
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getHiringdate() {
		return hiringdate;
	}
	public void setHiringdate(Date hiringdate) {
		this.hiringdate = hiringdate;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Employee(int employeeid, String name, String phone, String doornum, String streetname, String city,
			String state, String pincode, String uname, String upass, int shopid, double salary, Date hiringdate,
			Date dob, String gender, String email) {
		super();
		this.employeeid = employeeid;
		this.name = name;
		this.phone = phone;
		this.doornum = doornum;
		this.streetname = streetname;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.uname = uname;
		this.upass = upass;
		this.shopid = shopid;
		this.salary = salary;
		this.hiringdate = hiringdate;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", name=" + name + ", phone=" + phone + ", doornum=" + doornum
				+ ", streetname=" + streetname + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", uname=" + uname + ", upass=" + upass + ", shopid=" + shopid + ", salary=" + salary
				+ ", hiringdate=" + hiringdate + ", dob=" + dob + ", gender=" + gender + ", email=" + email + "]";
	}
	
	
}
