package com.example.demo3.models;

public class Feedback {
	int feedbackid;
	int orderid;
	int customerid;
	int websiterating;
	int employeerating;
	String feedbacktext;
	public int getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(int feedbackid) {
		this.feedbackid = feedbackid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getWebsiterating() {
		return websiterating;
	}
	public void setWebsiterating(int websiterating) {
		this.websiterating = websiterating;
	}
	public int getEmployeerating() {
		return employeerating;
	}
	public void setEmployeerating(int employeerating) {
		this.employeerating = employeerating;
	}
	public String getFeedbacktext() {
		return feedbacktext;
	}
	public void setFeedbacktext(String feedbacktext) {
		this.feedbacktext = feedbacktext;
	}
	public Feedback(int feedbackid, int orderid, int customerid, int websiterating, int employeerating,
			String feedbacktext) {
		super();
		this.feedbackid = feedbackid;
		this.orderid = orderid;
		this.customerid = customerid;
		this.websiterating = websiterating;
		this.employeerating = employeerating;
		this.feedbacktext = feedbacktext;
	}
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Feedback [feedbackid=" + feedbackid + ", orderid=" + orderid + ", customerid=" + customerid
				+ ", websiterating=" + websiterating + ", employeerating=" + employeerating + ", feedbacktext="
				+ feedbacktext + "]";
	}
}
