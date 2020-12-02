package com.example.demo3.models;

import java.util.Date;

public class Order {
	private int orderid;
	private int custid;
	private int assignedemployeeid;
	private String assignedemployeestatus;
	private double totalamount;
	private Date orderdatetime;
	private String deliveringto;
	private String paymentmode;
	private String orderstatus;
	private String deliverydoornum;
	private String deliverystreetname;
	private String deliverycity;
	private String deliverystate;
	private String deliverypincode;
	private String deliveryphone;
	private String paymentstatus;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public int getAssignedemployeeid() {
		return assignedemployeeid;
	}
	public void setAssignedemployeeid(int assignedemployeeid) {
		this.assignedemployeeid = assignedemployeeid;
	}
	public String getAssignedemployeestatus() {
		return assignedemployeestatus;
	}
	public void setAssignedemployeestatus(String assignedemployeestatus) {
		this.assignedemployeestatus = assignedemployeestatus;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public Date getOrderdatetime() {
		return orderdatetime;
	}
	public void setOrderdatetime(Date orderdatetime) {
		this.orderdatetime = orderdatetime;
	}
	public String getDeliveringto() {
		return deliveringto;
	}
	public void setDeliveringto(String deliveringto) {
		this.deliveringto = deliveringto;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getDeliverydoornum() {
		return deliverydoornum;
	}
	public void setDeliverydoornum(String deliverydoornum) {
		this.deliverydoornum = deliverydoornum;
	}
	public String getDeliverystreetname() {
		return deliverystreetname;
	}
	public void setDeliverystreetname(String deliverystreetname) {
		this.deliverystreetname = deliverystreetname;
	}
	public String getDeliverycity() {
		return deliverycity;
	}
	public void setDeliverycity(String deliverycity) {
		this.deliverycity = deliverycity;
	}
	public String getDeliverystate() {
		return deliverystate;
	}
	public void setDeliverystate(String deliverystate) {
		this.deliverystate = deliverystate;
	}
	public String getDeliverypincode() {
		return deliverypincode;
	}
	public void setDeliverypincode(String deliverypincode) {
		this.deliverypincode = deliverypincode;
	}
	public String getDeliveryphone() {
		return deliveryphone;
	}
	public void setDeliveryphone(String deliveryphone) {
		this.deliveryphone = deliveryphone;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public Order(int orderid, int custid, int assignedemployeeid, String assignedemployeestatus, double totalamount,
			Date orderdatetime, String deliveringto, String paymentmode, String orderstatus, String deliverydoornum,
			String deliverystreetname, String deliverycity, String deliverystate, String deliverypincode,
			String deliveryphone, String paymentstatus) {
		super();
		this.orderid = orderid;
		this.custid = custid;
		this.assignedemployeeid = assignedemployeeid;
		this.assignedemployeestatus = assignedemployeestatus;
		this.totalamount = totalamount;
		this.orderdatetime = orderdatetime;
		this.deliveringto = deliveringto;
		this.paymentmode = paymentmode;
		this.orderstatus = orderstatus;
		this.deliverydoornum = deliverydoornum;
		this.deliverystreetname = deliverystreetname;
		this.deliverycity = deliverycity;
		this.deliverystate = deliverystate;
		this.deliverypincode = deliverypincode;
		this.deliveryphone = deliveryphone;
		this.paymentstatus = paymentstatus;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", custid=" + custid + ", assignedemployeeid=" + assignedemployeeid
				+ ", assignedemployeestatus=" + assignedemployeestatus + ", totalamount=" + totalamount
				+ ", orderdatetime=" + orderdatetime + ", deliveringto=" + deliveringto + ", paymentmode=" + paymentmode
				+ ", orderstatus=" + orderstatus + ", deliverydoornum=" + deliverydoornum + ", deliverystreetname="
				+ deliverystreetname + ", deliverycity=" + deliverycity + ", deliverystate=" + deliverystate
				+ ", deliverypincode=" + deliverypincode + ", deliveryphone=" + deliveryphone + ", paymentstatus="
				+ paymentstatus + "]";
	}
	
}
