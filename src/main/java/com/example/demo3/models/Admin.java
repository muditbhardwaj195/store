package com.example.demo3.models;

import java.sql.Date;

public class Admin {
	private int adminid;
	private String name;
	private Date dob;
	private String phone;
	private String gender;
	private String doornum;
	private String streetname;
	private String city;
	private String state;
	private String pincode;
	private String uname;
	private String email;
	private String upass;
	private String imgfile;
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
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
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getImgfile() {
		return imgfile;
	}
	public void setImgfile(String imgfile) {
		this.imgfile = imgfile;
	}
	public Admin(int adminid, String name, Date dob, String phone, String gender, String doornum, String streetname,
			String city, String state, String pincode, String uname, String email, String upass, String imgfile) {
		super();
		this.adminid = adminid;
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
		this.upass = upass;
		this.imgfile = imgfile;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", name=" + name + ", dob=" + dob + ", phone=" + phone + ", gender="
				+ gender + ", doornum=" + doornum + ", streetname=" + streetname + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", uname=" + uname + ", email=" + email + ", upass=" + upass + ", imgfile="
				+ imgfile + "]";
	}
}
