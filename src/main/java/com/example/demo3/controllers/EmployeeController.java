package com.example.demo3.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo3.dao.AdminDao;
import com.example.demo3.dao.CustomerDao;
import com.example.demo3.dao.EmployeeDao;
import com.example.demo3.dao.OrderDao;
import com.example.demo3.dao.ProductDao;
import com.example.demo3.dao.ShopDao;
import com.example.demo3.dao.UserDao;
import com.example.demo3.models.Admin;
import com.example.demo3.models.Employee;
import com.example.demo3.models.Order;
import com.example.demo3.models.Product;
import com.example.demo3.models.Shop;
import com.example.demo3.models.ShowCartProduct;

@Controller
public class EmployeeController {
	@Autowired
	AdminDao admindao;

	@Autowired
	ProductDao productdao;

	@Autowired
	CustomerDao customerdao;

	@Autowired
	UserDao userdao;

	@Autowired
	EmployeeDao employeedao;

	@Autowired
	OrderDao orderdao;
	
	@Autowired
	ShopDao shopdao;

	@RequestMapping("/employee")
	public ModelAndView employeeWelcome(Principal principal) {
		ModelAndView model = new ModelAndView("employeehomepage");

		Employee employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		model.addObject("employee", employee);

		List<Order> orders = orderdao.getOrderListForEmployeeByEmployeeID(employee.getEmployeeid());
		model.addObject("orders", orders);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);

		return model;
	}

	@RequestMapping(value = "/employee/vieworderinfo/{orderid}", method = RequestMethod.GET)
	public ModelAndView employeeSeesOrderDetailsByIdView(Principal principal, @PathVariable int orderid) {
		System.out.println("Reached employeeSeesOrderDetailsByIdView");

		ModelAndView model = new ModelAndView("employeeorderdetailsbyid");

		Employee employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		Order order = orderdao.getOrderDetailsByOrderId(orderid);
		model.addObject("employee", employee);
		model.addObject("order", order);

		List<ShowCartProduct> showcartproducts = orderdao.getorderproductdetails(orderid);

		for (ShowCartProduct showcartproduct : showcartproducts) {
			showcartproduct.setProductname(productdao.getProductNameByProductId(showcartproduct.getProductid()));
		}

		model.addObject("showcartproducts", showcartproducts);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);
		return model;
	}

	@RequestMapping(value = "/employee/confirmorderdelivered/{orderid}", method = RequestMethod.GET)
	public ModelAndView employeeConfirmsOrderDeliveredDetailsByIdView(Principal principal, @PathVariable int orderid) {
		System.out.println("Reached employeeConfirmsOrderDeliveredDetailsByIdView");

		ModelAndView model = new ModelAndView("employeeconfirmorderdetailsbyid");

		Employee employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		model.addObject("employee", employee);
		Order order = orderdao.getOrderDetailsByOrderId(orderid);
		model.addObject("order", order);

		List<ShowCartProduct> showcartproducts = orderdao.getorderproductdetails(orderid);

		for (ShowCartProduct showcartproduct : showcartproducts) {
			showcartproduct.setProductname(productdao.getProductNameByProductId(showcartproduct.getProductid()));
		}

		model.addObject("showcartproducts", showcartproducts);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);

		return model;
	}

	@RequestMapping(value = "/employee/markorderdelivered/{orderid}", method = RequestMethod.POST)
	public ModelAndView employeeMarkOrderDeliveredDetailsByIdView(Principal principal, @PathVariable int orderid) {
		System.out.println("Reached employeeMarkOrderDeliveredDetailsByIdView");

		ModelAndView model = new ModelAndView("employeehomepage");

		Employee employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		model.addObject("employee", employee);

		orderdao.markOrderDeliveredByOrderId(orderid);

		List<Order> orders = orderdao.getOrderListForEmployeeByEmployeeID(employee.getEmployeeid());
		model.addObject("orders", orders);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);
		model.addObject("msg", "Order Marked As Delivered Successfully");
		return model;
	}

	@RequestMapping("/employee/allorderassignedtoemployee")
	public ModelAndView employeeAllOrdersAssignedDetails(Principal principal) {
		ModelAndView model = new ModelAndView("employeeallassignedorders");

		Employee employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		model.addObject("employee", employee);

		List<Order> orders = orderdao.getAllAssignedOrderListForEmployeeByEmployeeID(employee.getEmployeeid());
		model.addObject("orders", orders);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);

		return model;
	}

	@RequestMapping("/employee/profilepage")
	public ModelAndView employeeViewProfile(Principal principal) {
		ModelAndView model = new ModelAndView("employeeviewprofile");

		Employee employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		model.addObject("employee", employee);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);

		return model;
	}

	@RequestMapping("/employee/changeprofiledetails")
	public ModelAndView employeeEditProfileView(Principal principal) {
		ModelAndView model = new ModelAndView("employeeEditProfileView");

		Employee employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		model.addObject("employee", employee);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);

		return model;
	}

	@RequestMapping(value = "/employee/changeprofiledetails", method = RequestMethod.POST)
	public ModelAndView employeeEditProfileView(Principal principal, @RequestParam Map<String, String> reqmap) {
		System.out.println("Reached Admin employeeEditProfileView ");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = new Date(0);
		try {
			parsed = (Date) format.parse(reqmap.get("dob"));
		} catch (Exception e) {
			throw new RuntimeException("");
		}
		java.sql.Date enteredDate = new java.sql.Date(parsed.getTime());
		Employee employee = new Employee();
		employee.setCity(reqmap.get("city"));
		employee.setDob(enteredDate);
		employee.setDoornum(reqmap.get("doornum"));
		employee.setGender(reqmap.get("gender"));
		employee.setName(reqmap.get("name"));
		employee.setPincode(reqmap.get("pincode"));
		employee.setPhone(reqmap.get("phone"));
		employee.setStreetname(reqmap.get("streetname"));
		employee.setState(reqmap.get("state"));
		employee.setUname(principal.getName());

		employeedao.updateEmployeeDetails(employee);
		ModelAndView model = new ModelAndView("employeeviewprofile");

		employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		model.addObject("employee", employee);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);
		
		model.addObject("msg","Profile Succesfully Update.");

		return model;
	}
	
	@RequestMapping("/employee/contact")
	public ModelAndView employeeContactView(Principal principal) {
		System.out.println("Reached employeeContactView");

		ModelAndView model = new ModelAndView("employeecontact");

		Employee employee = employeedao.getEmployeeDetailsByUsername(principal.getName());
		model.addObject("employee", employee);

		int unDeliveredOrders = orderdao.tellEmployeeTotalUndeliveredAssignedOrder(employee.getEmployeeid());
		model.addObject("unDeliveredOrders", unDeliveredOrders);

		List<Shop> shops = shopdao.getAllShopsDetails();
		model.addObject("shops", shops);
		
		return model;
	}

}
