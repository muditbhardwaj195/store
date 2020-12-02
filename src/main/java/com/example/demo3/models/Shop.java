package com.example.demo3.models;

public class Shop {
	private int shopid;
	private String shopdescription;
	private String phone;
	private String doornum;
	private String streetname;
	private String city;
	private String state;
	private String pincode;
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getShopdescription() {
		return shopdescription;
	}
	public void setShopdescription(String shopdescription) {
		this.shopdescription = shopdescription;
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
	public Shop(int shopid, String shopdescription, String phone, String doornum, String streetname, String city,
			String state, String pincode) {
		super();
		this.shopid = shopid;
		this.shopdescription = shopdescription;
		this.phone = phone;
		this.doornum = doornum;
		this.streetname = streetname;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Shop [shopid=" + shopid + ", shopdescription=" + shopdescription + ", phone=" + phone + ", doornum="
				+ doornum + ", streetname=" + streetname + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + "]";
	}
}
