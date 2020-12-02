package com.example.demo3.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.example.demo3.dao.SupplierDao;
import com.example.demo3.dao.UserDao;
import com.example.demo3.models.Admin;
import com.example.demo3.models.Customer;
import com.example.demo3.models.Employee;
import com.example.demo3.models.Feedback;
import com.example.demo3.models.Order;
import com.example.demo3.models.Product;
import com.example.demo3.models.Shop;
import com.example.demo3.models.ShowCartProduct;
import com.example.demo3.models.SupplierInfo;
import com.example.demo3.models.Users;

@Controller
public class AdminController 
{
	
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
	SupplierDao supplierdao;
	
	@Autowired
	ShopDao shopdao;
	
	@RequestMapping("/admin")
	public ModelAndView adminwelcome(Principal principal) 
	{
		ModelAndView model = new ModelAndView("adminhomepage");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Product> products = productdao.getAllProducts();
		model.addObject("products", products);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(path = "/admin", method = RequestMethod.POST)
	public ModelAndView adminWelcomeFilterView(Principal principal, @RequestParam Map<String,String> reqmap) {
		System.out.println("Reached adminWelcomeFilterView");
		ModelAndView model = new ModelAndView("adminhomepage");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int choice = Integer.parseInt(reqmap.get("filtervalue"));
		
		List<Product> products = new ArrayList<Product>();
		
		if(choice == 1)
		{
			products = productdao.getAllProducts();
		}
		else if(choice == 2)
		{
			products = productdao.getAllProductsByNameSortedAsc();
		}
		else if(choice == 3)
		{
			products = productdao.getAllProductsByNameSortedDesc();
		}
		else if(choice == 4)
		{
			products = productdao.getAllProductsByPriceSortedAsc();
		}
		else if(choice == 5)
		{
			products = productdao.getAllProductsByNameSortedDesc();
		}
		
		model.addObject("products", products);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Filter applied.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/store/{productid}")
	public ModelAndView showSingleProduct(Principal principal, @PathVariable int productid) {
		System.out.println("Reached showSingleView for admin");

		ModelAndView model = new ModelAndView("adminsingleproductview");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);

		Product product = productdao.getSingleProduct(productid);
		model.addObject("product", product);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(path = "/admin/seeprofilepage", method = RequestMethod.GET)
	public ModelAndView adminSeeProfileView(Principal principal) {
		System.out.println("Reached adminSeeProfileView");
		ModelAndView model = new ModelAndView("adminseeprofilepage");
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(path = "/admin/editprofilepage", method = RequestMethod.GET)
	public ModelAndView adminProfileView(Principal principal) {
		System.out.println("Reached admin Profile view");
		ModelAndView model = new ModelAndView("adminprofilepage");
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value="/admin/editprofiledetails", method = RequestMethod.POST)
	public ModelAndView customerProfileUpdaterView(Principal principal, @RequestParam Map<String,String> reqmap ) 
	{
		System.out.println("Reached Admin editprofileView " + reqmap.get("dob"));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed=new Date(0);
        try {
        	parsed = (Date) format.parse(reqmap.get("dob"));
		} catch (Exception e) {
			throw new RuntimeException("");
		}
        java.sql.Date enteredDate = new java.sql.Date(parsed.getTime()); 
		Admin admin = new Admin();
		admin.setCity(reqmap.get("city"));
		admin.setDoornum(reqmap.get("doornum"));
		admin.setGender(reqmap.get("gender"));
		admin.setName(reqmap.get("name"));
		admin.setPhone(reqmap.get("phone"));
		admin.setPincode(reqmap.get("pincode"));
		admin.setState(reqmap.get("state"));
		admin.setStreetname(reqmap.get("streetname"));
		admin.setUname(principal.getName());
		admin.setDob(enteredDate);
		
		admindao.updateAdminDetails(admin);
		
		ModelAndView model = new ModelAndView("adminseeprofilepage");
		admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg","Information Updated Successfully.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(path = "/admin/customerdetails", method = RequestMethod.GET)
	public ModelAndView adminSeesCustomerDetails(Principal principal) {
		System.out.println("Reached adminSeesCustomerDetails");
		ModelAndView model = new ModelAndView("adminseescustomerdetails");
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Customer> customers = customerdao.getAllCustomerDetails();
		model.addObject("customers",customers);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/viewcustinfo/{customeruname}")
	public ModelAndView showCustomerInfoToAdmin(Principal principal, @PathVariable String customeruname) {
		System.out.println("Reached showCustomerInfoToAdmin");

		ModelAndView model = new ModelAndView("customerinfotoadmin");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);

		Customer customer = customerdao.getUserByUsername(customeruname);
		model.addObject("customer",customer);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/viewcustinfobyid/{customerid}")
	public ModelAndView showCustomerInfoByIdToAdmin(Principal principal, @PathVariable Integer customerid) {
		System.out.println("Reached showCustomerInfoByIdToAdmin");

		ModelAndView model = new ModelAndView("customerinfotoadmin");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);

		Customer customer = customerdao.getUserByUserId(customerid);
		model.addObject("customer",customer);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/deletecustomer/{customeruname}")
	public ModelAndView deleteCustomerFromAdmin(Principal principal, @PathVariable String customeruname) {
		System.out.println("Reached showCustomerInfoToAdmin");
		/*
		 * ModelAndView model = new ModelAndView("redirect:/admin/customerdetails");
		 */
		
		userdao.deleteUserWithUsername(customeruname);
		
		ModelAndView model = new ModelAndView("adminseescustomerdetails");
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Customer> customers = customerdao.getAllCustomerDetails();
		model.addObject("customers",customers);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg","Customer Deleted Successfully.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addnewadmin")
	public ModelAndView addNewAdmin(Principal principal) {
		System.out.println("Reached addNewAdmin");

		ModelAndView model = new ModelAndView("addnewadmin");
		Users user = new Users();
		model.addObject("user",user);
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addnewadmin", method = RequestMethod.POST)
	public ModelAndView addNewAdminCheckView(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult,Principal principal) {

		System.out.println("Reached addNewAdminCheckView");

		ModelAndView model = new ModelAndView();
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);

		if (bindingResult.hasErrors()) {
			model.setViewName("addnewadmin");
			return model;
		}

		if ((user.getPassword().equals(user.getRepassword())) == false) {
			model.setViewName("addnewadmin");
			model.addObject("msg", "Password does not match...");
			model.addObject("colorOfInstruction","red");
			return model;
		}

		if (userdao.checkifusernameexists(user.getUsername())) {
			model.setViewName("addnewadmin");
			model.addObject("msg", "Account with this username already exist..");
			model.addObject("colorOfInstruction","red");
			return model;
		}

		if (userdao.checkifusernamewiththisemailexists(user.getEmail())) {
			model.setViewName("addnewadmin");
			model.addObject("msg", "Account with this email already exist..");
			model.addObject("colorOfInstruction","red");
			return model;
		}

		user.setUserRole("ROLE_ADMIN");
		System.out.println("Ready to add user to database");
		userdao.addUser(user);
		admindao.addAdminFromUser(user);
		
		
		
		model = new ModelAndView("adminseesadmindetails");
		admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Admin> admins = admindao.getAllAdminDetails(); 
		model.addObject("admins",admins);
		
		unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Admin account created successfully.");
		model.addObject("colorOfInstruction","green");
		
		return model;

	}
	
	@RequestMapping(path = "/admin/admindetails", method = RequestMethod.GET)
	public ModelAndView adminSeesAllAdminDetails(Principal principal) {
		System.out.println("Reached adminSeesAllAdminDetails");
		ModelAndView model = new ModelAndView("adminseesadmindetails");
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Admin> admins = admindao.getAllAdminDetails(); 
		model.addObject("admins",admins);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/viewadmininfo/{adminuname}")
	public ModelAndView showAdminInfoToAdmin(Principal principal, @PathVariable String adminuname) {
		System.out.println("Reached showAdminInfoToAdmin");

		ModelAndView model = new ModelAndView("admininfotoadmin");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Admin admin2 = admindao.getAdminDetailsByUsername(adminuname);
		model.addObject("admin2",admin2);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);

		return model;
	}
	
	@RequestMapping(value = "/admin/deleteadmin/{adminuname}")
	public ModelAndView deleteAdminFromAdmin(Principal principal, @PathVariable String adminuname) {
		System.out.println("Reached deleteAdminFromAdmin");
		
		userdao.deleteUserWithUsername(adminuname);
		
		ModelAndView model = new ModelAndView("adminseesadmindetails");
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Admin> admins = admindao.getAllAdminDetails(); 
		model.addObject("admins",admins);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Admin account deleted successfully.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addnewemployee")
	public ModelAndView addNewEmployee(Principal principal) {
		System.out.println("Reached addNewEmployee");

		ModelAndView model = new ModelAndView("addnewemployee");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value="/admin/addnewemployee", method = RequestMethod.POST)
	public ModelAndView addNewEmployeeToDatabaseView(Principal principal, @RequestParam Map<String,String> reqmap ) 
	{
		System.out.println("Reached Admin addNewEmployeeToDatabaseView ");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parseddob=new Date(0);
        try {
        	parseddob = (Date) format.parse(reqmap.get("dob"));
		} catch (Exception e) {
			throw new RuntimeException("");
		}
        java.sql.Date enteredDateOfBirth = new java.sql.Date(parseddob.getTime()); 
        Date parsedhiringdate=new Date(0);
        try {
        	parsedhiringdate = (Date) format.parse(reqmap.get("hiringdate"));
		} catch (Exception e) {
			throw new RuntimeException("");
		}
        java.sql.Date enteredHiringDate = new java.sql.Date(parsedhiringdate.getTime()); 
        ModelAndView model = new ModelAndView();
        
        Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
        
        if ((reqmap.get("password").equals(reqmap.get("repassword"))) == false) {
			model.setViewName("addnewemployee");
			
			int unassignedorder = orderdao.tellAdminUnassignedOrder();
			model.addObject("unassignedorder",unassignedorder);
			
			model.addObject("msg", "Password does not match...");
			model.addObject("colorOfInstruction","red");
			return model;
		}

		if (userdao.checkifusernameexists(reqmap.get("uname"))) {
			model.setViewName("addnewemployee");
			
			int unassignedorder = orderdao.tellAdminUnassignedOrder();
			model.addObject("unassignedorder",unassignedorder);
			
			model.addObject("msg", "Account with this username already exist..");
			model.addObject("colorOfInstruction","red");
			return model;
		}

		if (userdao.checkifusernamewiththisemailexists(reqmap.get("email"))) {
			model.setViewName("addnewemployee");
			
			int unassignedorder = orderdao.tellAdminUnassignedOrder();
			model.addObject("unassignedorder",unassignedorder);
			
			model.addObject("msg", "Account with this email already exist..");
			model.addObject("colorOfInstruction","red");
			return model;
		}
        
        Employee employee = new Employee();
        employee.setDob(enteredDateOfBirth);
        employee.setEmail(reqmap.get("email"));
        employee.setGender(reqmap.get("gender"));
        employee.setHiringdate(enteredHiringDate);
        employee.setName(reqmap.get("name"));
        employee.setPhone(reqmap.get("phone"));
        employee.setSalary(Float.parseFloat(reqmap.get("salary")));
        employee.setShopid(Integer.parseInt(reqmap.get("shopid")));
        employee.setUname(reqmap.get("username"));
        employee.setUpass(reqmap.get("password"));
        
        userdao.addUserFromEmployee(employee);
        employeedao.addEmployee(employee);
		
		model = new ModelAndView("adminseesemployeedetails");
		
		admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Employee> employees = employeedao.getAllEmployeeDetails(); 
		model.addObject("employees",employees);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Employee added successfully");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(path = "/admin/employeedetails", method = RequestMethod.GET)
	public ModelAndView adminSeesAllEmployeeDetails(Principal principal) {
		System.out.println("Reached adminSeesAllEmployeeDetails");
		ModelAndView model = new ModelAndView("adminseesemployeedetails");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Employee> employees = employeedao.getAllEmployeeDetails(); 
		model.addObject("employees",employees);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/viewemployeeinfo/{employeeuname}")
	public ModelAndView showEmployeeInfoToAdmin(Principal principal, @PathVariable String employeeuname) {
		System.out.println("Reached showEmployeeInfoToAdmin");

		ModelAndView model = new ModelAndView("employeeinfotoadmin");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Employee employee = employeedao.getEmployeeDetailsByUsername(employeeuname);
		model.addObject("employee",employee);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);

		return model;
	}
	
	@RequestMapping(value = "/admin/viewemployeeinfobyid/{employeeid}", method = RequestMethod.GET)
	public ModelAndView adminEmployeeDetailsByIdView(Principal principal, @PathVariable int employeeid) 
	{
		System.out.println("Reached adminEmployeeDetailsByIdView");

		ModelAndView model = new ModelAndView("employeeinfotoadmin");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Employee employee = employeedao.getEmployeeDetailsByID(employeeid);
		model.addObject("employee",employee);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);

		return model;
	}
	
	@RequestMapping(value = "/admin/editemployeesalaryandshopid/{employeeuname}")
	public ModelAndView editEmployeeSalaryView(Principal principal, @PathVariable String employeeuname) {
		System.out.println("Reached editEmployeeSalaryView");

		ModelAndView model = new ModelAndView("editEmployeeSalary");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Employee employee = employeedao.getEmployeeDetailsByUsername(employeeuname);
		model.addObject("employee",employee);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);

		return model;
	}
	
	@RequestMapping(value = "/admin/editemployeesalaryandshopid/{employeeuname}", method = RequestMethod.POST)
	public ModelAndView editEmployeeSalary(Principal principal, @PathVariable String employeeuname, @RequestParam Map<String,String> reqmap) {
		System.out.println("Reached editEmployeeSalary");

//		ModelAndView model = new ModelAndView("redirect:/admin/viewemployeeinfo/{employeeuname}");
		
		employeedao.setSalaryForEmployeeByUsername(Float.parseFloat(reqmap.get("salary")),employeeuname);
		
		employeedao.setShopidForEmployeeByUsername(Integer.parseInt(reqmap.get("shopid")),employeeuname);

		ModelAndView model = new ModelAndView("employeeinfotoadmin");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Employee employee = employeedao.getEmployeeDetailsByUsername(employeeuname);
		model.addObject("employee",employee);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Employee details changed successfully");
		model.addObject("colorOfInstruction","green");

		return model;
	}
	
	@RequestMapping(value = "/admin/deleteemployee/{employeeuname}")
	public ModelAndView deleteEmployeeFromAdmin(Principal principal, @PathVariable String employeeuname) {
		System.out.println("Reached deleteEmployeeFromAdmin");
		
		userdao.deleteUserWithUsername(employeeuname);
		
		ModelAndView model = new ModelAndView("adminseesemployeedetails");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Employee> employees = employeedao.getAllEmployeeDetails(); 
		model.addObject("employees",employees);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Employee account deleted successfully");
		model.addObject("colorOfInstruction","green");
		return model;
	}
	
	//	Admin-Product interface 
	
	@RequestMapping(path = "/admin/productdetails", method = RequestMethod.GET)
	public ModelAndView adminSeesAllProductDetails(Principal principal) {
		System.out.println("Reached adminSeesAllProductDetails");
		ModelAndView model = new ModelAndView("adminseesproductdetails");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Product> products = productdao.getAllProducts(); 
		model.addObject("products",products);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/viewproductinfo/{productid}")
	public ModelAndView showProductInfoToAdmin(Principal principal, @PathVariable String productid) {
		System.out.println("Reached showProductInfoToAdmin");

		ModelAndView model = new ModelAndView("productinfotoadmin");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Product product = productdao.getSingleProduct(Integer.parseInt(productid));
		model.addObject("product",product);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);

		return model;
	}
	
	@RequestMapping(value = "/admin/editproductmrpandquantity/{productid}", method = RequestMethod.POST)
	public ModelAndView editProductMrpAndQuantity(Principal principal, @PathVariable Integer productid, @RequestParam Map<String,String> reqmap) {
		System.out.println("Reached editProductMrpAndQuantity");
		
		productdao.editProductMrpByProductId(productid,Float.parseFloat(reqmap.get("mrp")));
		
		productdao.editProductAddQuantityByProductId(productid,Integer.parseInt(reqmap.get("addquantity")));

		ModelAndView model = new ModelAndView("productinfotoadmin");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Product product = productdao.getSingleProduct(productid);
		model.addObject("product",product);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Product Information Changed.");
		model.addObject("colorOfInstruction","green");

		return model;
	}
	
	@RequestMapping(value = "/admin/addnewproduct")
	public ModelAndView addNewProduct(Principal principal) {
		System.out.println("Reached addNewProduct");

		ModelAndView model = new ModelAndView("addnewproduct");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addnewproduct", method = RequestMethod.POST)
	public ModelAndView addNewProductToTable(Principal principal, @RequestParam Map<String,String> reqmap) {
		System.out.println("Reached editProductMrpAndQuantity");
		
		Product product = new Product();
		product.setBrand(reqmap.get("brand"));
		product.setDescription(reqmap.get("description"));
		product.setImname(reqmap.get("imname"));
		product.setMrp(Float.parseFloat(reqmap.get("mrp")));
		product.setName(reqmap.get("name"));
		product.setQtyinstock(Integer.parseInt(reqmap.get("qtyinstock")));
		
		productdao.addProductByAdmin(product);
		
		ModelAndView model = new ModelAndView("adminseesproductdetails");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Product> products = productdao.getAllProducts(); 
		model.addObject("products",products);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Product Added Successfully.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(path = "/admin/orderdetails", method = RequestMethod.GET)
	public ModelAndView adminSeesAllOrderDetails(Principal principal) {
		System.out.println("Reached adminSeesAllOrderDetails");
		
		ModelAndView model = new ModelAndView("adminseesallorderdetails");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Order> orders = orderdao.getAllOrdersForAdmin();
		model.addObject("orders",orders);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/vieworderinfo/{orderid}", method = RequestMethod.GET)
	public ModelAndView adminOrderDetailsByIdView(Principal principal, @PathVariable int orderid) 
	{
		System.out.println("Reached adminOrderDetailsByIdView");

		ModelAndView model = new ModelAndView("adminorderdetailsbyid");

		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Order order = orderdao.getOrderDetailsByOrderId(orderid);
		model.addObject("order",order);
		
		List<ShowCartProduct> showcartproducts = orderdao.getorderproductdetails(orderid);
		
		for(ShowCartProduct showcartproduct : showcartproducts)
		{
			showcartproduct.setProductname(productdao.getProductNameByProductId(showcartproduct.getProductid()));
		}
		
		model.addObject("showcartproducts",showcartproducts);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(path = "/admin/assignemployee/{orderid}", method = RequestMethod.GET)
	public ModelAndView adminAssignEmployeeToOrder(Principal principal, @PathVariable int orderid) {
		System.out.println("Reached adminAssignEmployeeToOrder");
		ModelAndView model = new ModelAndView("adminassignemployee");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Employee> employees = employeedao.getAllEmployeeDetails(); 
		model.addObject("employees",employees);
		
		Order order = orderdao.getOrderDetailsByOrderId(orderid);
		model.addObject("order",order);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(path = "/admin/assignemployee/{orderid}", method = RequestMethod.POST)
	public ModelAndView adminFinallyAssignEmployeeToOrder(Principal principal, @PathVariable int orderid, @RequestParam Map<String,String> reqmap) {
		System.out.println("Reached adminFinallyAssignEmployeeToOrder");
		
		int employeeid = Integer.parseInt(reqmap.get("employeeid"));
		orderdao.assignEmployeeByOrderId(employeeid,orderid);
//		ModelAndView model = new ModelAndView("redirect:/admin/vieworderinfo/{orderid}");
		
		ModelAndView model = new ModelAndView("adminorderdetailsbyid");

		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		Order order = orderdao.getOrderDetailsByOrderId(orderid);
		model.addObject("order",order);
		
		List<ShowCartProduct> showcartproducts = orderdao.getorderproductdetails(orderid);
		
		for(ShowCartProduct showcartproduct : showcartproducts)
		{
			showcartproduct.setProductname(productdao.getProductNameByProductId(showcartproduct.getProductid()));
		}
		
		model.addObject("showcartproducts",showcartproducts);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Employee Assigned.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addnewsupplier")
	public ModelAndView addNewSupplier(Principal principal) {
		System.out.println("Reached addNewSupplier");

		ModelAndView model = new ModelAndView("addnewsupplier");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addnewsupplier", method = RequestMethod.POST)
	public ModelAndView addNewSupplierToTable(Principal principal, @RequestParam Map<String,String> reqmap) {
		System.out.println("Reached addNewSupplierToTable");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parseddob=new Date(0);
        try {
        	parseddob = (Date) format.parse(reqmap.get("dob"));
		} catch (Exception e) {
			throw new RuntimeException("");
		}
        java.sql.Date enteredDateOfBirth = new java.sql.Date(parseddob.getTime()); 
		
		SupplierInfo supplier = new SupplierInfo();
		supplier.setCity(reqmap.get("city"));
		supplier.setDoornum(reqmap.get("doornum"));
		supplier.setEmail(reqmap.get("email"));
		supplier.setName(reqmap.get("name"));
		supplier.setPhone(reqmap.get("phone"));
		supplier.setPincode(reqmap.get("pincode"));
		supplier.setState(reqmap.get("state"));
		supplier.setStreetname(reqmap.get("streetname"));
		supplier.setDob(enteredDateOfBirth);
		
		supplierdao.addSupplierByAdmin(supplier);
		
		ModelAndView model = new ModelAndView("adminseesallsupplierdetails");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<SupplierInfo> suppliers = supplierdao.getAllSuppliersForAdmin();
		model.addObject("suppliers",suppliers);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg","Supplier added successfully.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(path = "/admin/supplierdetails", method = RequestMethod.GET)
	public ModelAndView adminSeesAllSupplierDetails(Principal principal) {
		System.out.println("Reached adminSeesAllSupplierDetails");
		
		ModelAndView model = new ModelAndView("adminseesallsupplierdetails");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<SupplierInfo> suppliers = supplierdao.getAllSuppliersForAdmin();
		model.addObject("suppliers",suppliers);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/viewsupplierinfo/{supplierid}", method = RequestMethod.GET)
	public ModelAndView adminSupplierDetailsByIdView(Principal principal, @PathVariable int supplierid) 
	{
		System.out.println("Reached adminSupplierDetailsByIdView");

		ModelAndView model = new ModelAndView("adminsupplierdetailsbyid");

		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		SupplierInfo supplier = supplierdao.getSupplierDetailById(supplierid);
		model.addObject("supplier",supplier);
		
		List<Product> products = productdao.getAllProducts(); 
		
		List<Integer> productids = supplierdao.getAllProductsOfSupplierByID(supplierid);
		
		List<Product> productsupplied = new ArrayList<Product>();
		
		List<Product> productnotsupplied = new ArrayList<Product>();
		
		for(Product product: products)
		{
			boolean found = false;
			for(int tmpproductid : productids)
			{
				if(tmpproductid == product.getId())
				{
					found = true;
					break;
				}
			}
			if(found)
			{
				productsupplied.add(product);
			}
			else
			{
				productnotsupplied.add(product);
			}
		}
		
		model.addObject("productsupplied",productsupplied);
		model.addObject("productnotsupplied",productnotsupplied);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addproducttosupplier/{supplierid}/{productid}", method = RequestMethod.GET)
	public ModelAndView adminAddProductToSupplier(Principal principal, @PathVariable("supplierid") Integer supplierid,@PathVariable("productid") Integer productid) 
	{
		System.out.println("Reached adminAddProductToSupplier" + supplierid + productid);
		
		supplierdao.addProductToSupplier(supplierid,productid);
		
		ModelAndView model = new ModelAndView("adminsupplierdetailsbyid");

		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		SupplierInfo supplier = supplierdao.getSupplierDetailById(supplierid);
		model.addObject("supplier",supplier);
		
		List<Product> products = productdao.getAllProducts(); 
		
		List<Integer> productids = supplierdao.getAllProductsOfSupplierByID(supplierid);
		
		List<Product> productsupplied = new ArrayList<Product>();
		
		List<Product> productnotsupplied = new ArrayList<Product>();
		
		for(Product product: products)
		{
			boolean found = false;
			for(int tmpproductid : productids)
			{
				if(tmpproductid == product.getId())
				{
					found = true;
					break;
				}
			}
			if(found)
			{
				productsupplied.add(product);
			}
			else
			{
				productnotsupplied.add(product);
			}
		}
		
		model.addObject("productsupplied",productsupplied);
		model.addObject("productnotsupplied",productnotsupplied);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg","Product added to suppliers list.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/removeproductfromsupplier/{supplierid}/{productid}", method = RequestMethod.GET)
	public ModelAndView adminRemoveProductFromSupplier(Principal principal, @PathVariable("supplierid") Integer supplierid,@PathVariable("productid") Integer productid) 
	{
		System.out.println("Reached adminRemoveProductToSupplier");
		
		supplierdao.RemoveProductFromSupplier(supplierid,productid);
		
		ModelAndView model = new ModelAndView("adminsupplierdetailsbyid");

		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin",admin);
		
		SupplierInfo supplier = supplierdao.getSupplierDetailById(supplierid);
		model.addObject("supplier",supplier);
		
		List<Product> products = productdao.getAllProducts(); 
		
		List<Integer> productids = supplierdao.getAllProductsOfSupplierByID(supplierid);
		
		List<Product> productsupplied = new ArrayList<Product>();
		
		List<Product> productnotsupplied = new ArrayList<Product>();
		
		for(Product product: products)
		{
			boolean found = false;
			for(int tmpproductid : productids)
			{
				if(tmpproductid == product.getId())
				{
					found = true;
					break;
				}
			}
			if(found)
			{
				productsupplied.add(product);
			}
			else
			{
				productnotsupplied.add(product);
			}
		}
		
		model.addObject("productsupplied",productsupplied);
		model.addObject("productnotsupplied",productnotsupplied);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg","Product deleted from suppliers list.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(path = "/admin/feedbackdetails", method = RequestMethod.GET)
	public ModelAndView adminSeesAllFeedbackDetails(Principal principal) {
		System.out.println("Reached adminSeesAllFeedbackDetails");
		
		ModelAndView model = new ModelAndView("adminseesallfeedbackdetails");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Feedback> feedbacks = admindao.getAllFeedbackForAdmin();
		model.addObject("feedbacks",feedbacks);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(path = "/admin/shopdetails", method = RequestMethod.GET)
	public ModelAndView adminSeesShopDetails(Principal principal) {
		System.out.println("Reached adminSeesShopDetails");
		ModelAndView model = new ModelAndView("adminseesshopdetails");
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Shop> shops = shopdao.getAllShopsDetails();
		model.addObject("shops",shops);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addnewshop")
	public ModelAndView addNewShop(Principal principal) {
		System.out.println("Reached addNewShop");

		ModelAndView model = new ModelAndView("addnewshop");
		
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addnewshop", method = RequestMethod.POST)
	public ModelAndView addNewShopToTable(Principal principal, @RequestParam Map<String,String> reqmap) {
		System.out.println("Reached addNewShopToTable");
		
		Shop shop = new Shop();
		shop.setCity(reqmap.get("city"));
		shop.setDoornum(reqmap.get("doornum"));
		shop.setPhone(reqmap.get("phone"));
		shop.setPincode(reqmap.get("pincode"));
		shop.setShopdescription(reqmap.get("shopdescription"));
		shop.setState(reqmap.get("state"));
		shop.setStreetname(reqmap.get("streetname"));
		
		shopdao.addShopByAdmin(shop);
		
		ModelAndView model = new ModelAndView("adminseesshopdetails");
		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);
		
		List<Shop> shops = shopdao.getAllShopsDetails();
		model.addObject("shops",shops);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		model.addObject("msg", "Shop Added Successfully.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping("/admin/contact")
	public ModelAndView adminContactView(Principal principal) {
		System.out.println("Reached adminContactView");

		ModelAndView model = new ModelAndView("admincontact");

		Admin admin = admindao.getAdminDetailsByUsername(principal.getName());
		model.addObject("admin", admin);

		List<Shop> shops = shopdao.getAllShopsDetails();
		model.addObject("shops", shops);
		
		int unassignedorder = orderdao.tellAdminUnassignedOrder();
		model.addObject("unassignedorder",unassignedorder);
		
		return model;
	}
	
	@RequestMapping(value = "/createNewAdmin123654789")
	public ModelAndView createNewAdminByUrl() {
		System.out.println("Reached createNewAdmin");

		ModelAndView model = new ModelAndView("redirect:/login");
		
		Users user = new Users();
		user.setUsername("admin");
		user.setName("Admin");
		user.setEmail("admin@gmail.com");
		user.setPassword("admin");
		user.setPhone("1236547890");
		user.setRepassword("admin");
		
		if(userdao.checkifusernameexists(user.getUsername())) {
			return model;
		}
		user.setUserRole("ROLE_ADMIN");
		System.out.println("Ready to add user to database");
		userdao.addUser(user);
		admindao.addAdminFromUser(user);
		return model;
	}
}
