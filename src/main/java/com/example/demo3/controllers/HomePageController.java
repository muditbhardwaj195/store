package com.example.demo3.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.demo3.models.ShowCartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import com.example.demo3.dao.OrderDao;
import com.example.demo3.dao.ProductDao;
import com.example.demo3.dao.ShopDao;
import com.example.demo3.dao.UserDao;
import com.example.demo3.models.Admin;
import com.example.demo3.models.Customer;
import com.example.demo3.models.Feedback;
import com.example.demo3.models.Order;
import com.example.demo3.models.Product;
import com.example.demo3.models.Shop;
import com.example.demo3.models.Users;

@Controller
public class HomePageController {

	@Autowired
	public UserDao userdao;

	@Autowired
	public CustomerDao customerdao;

	@Autowired
	public ProductDao productdao;
	
	@Autowired
	public AdminDao admindao;
	
	@Autowired
	public OrderDao orderdao;
	
	@Autowired
	public ShopDao shopdao;

	@RequestMapping("/home")
	public String userlogout() {
		return "homepage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginView() {
		System.out.println("Reached login view");
		ModelAndView m = new ModelAndView();
		m.setViewName("login");
		m.addObject("msg", "");
		return m;
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginFailedView() {
		System.out.println("Reached login failed view");
		ModelAndView m = new ModelAndView();
		m.setViewName("login");
		m.addObject("msg", "Invalid Credentials");
		return m;
	}

	@RequestMapping(path = "/check", method = RequestMethod.GET)
	public ModelAndView checkView(Principal principal) {
		System.out.println("Reached check view");
		ModelAndView model = new ModelAndView("temp");
		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);
		Users user = new Users();
		model.addObject("user",user);
		return model;
	}

	@RequestMapping("/register")
	public ModelAndView registerView(HttpServletRequest request) {
		System.out.println("Reached registerView");
		ModelAndView model = new ModelAndView("register");
		model.addObject("msg", "");
		Users user = new Users();
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView submitsignupform(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult) {

		System.out.println("Reached sumbitsignupform");
		System.out.println(user.toString());

		ModelAndView m = new ModelAndView();

		if (bindingResult.hasErrors()) {
			m.setViewName("register");
			return m;
		}

		System.out.println(user.getPassword().equals(user.getRepassword()));

		if ((user.getPassword().equals(user.getRepassword())) == false) {
			m.setViewName("register");
			m.addObject("msg", "Password does not match...");
			return m;
		}

		if (userdao.checkifusernameexists(user.getUsername())) {
			m.setViewName("register");
			m.addObject("msg", "Account with this username already exist..");
			return m;
		}

		if (userdao.checkifusernamewiththisemailexists(user.getEmail())) {
			m.setViewName("register");
			m.addObject("msg", "Account with this email already exist..");
			return m;
		}

		user.setUserRole("ROLE_CUSTOMER");
		System.out.println(user.toString());
		System.out.println("Ready to add user to database");
		userdao.addUser(user);
//		admindao.addAdminFromUser(user);
		customerdao.addCustomerFromUser(user);
//		return "redirect:/login";
		m.setViewName("login");
		return m;

	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

	@RequestMapping("/customer")
	public ModelAndView customerWelcomeView(Principal principal) {
		System.out.println("Reached customer welcome view");

		ModelAndView model = new ModelAndView("store");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);
//		System.out.println(customer.toString());

		List<Product> products = productdao.getAllProducts();
		System.out.println(products);
		model.addObject("products", products);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		return model;
	}
	
	@RequestMapping(path = "/customer", method = RequestMethod.POST)
	public ModelAndView customerWelcomeFilterView(Principal principal, @RequestParam Map<String,String> reqmap) {
		System.out.println("Reached customerWelcomeFilterView");
		ModelAndView model = new ModelAndView("store");
		
		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);
		
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
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		model.addObject("msg", "Filter applied.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}

	@RequestMapping(value = "/customer/store/{productid}")
	public ModelAndView showSingleProduct(Principal principal, @PathVariable int productid) {
		System.out.println("Reached showSingleView");

		ModelAndView model = new ModelAndView("singleProductView");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);

		Product product = productdao.getSingleProduct(productid);
		model.addObject("product", product);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		return model;
	}

	@RequestMapping(value = "/customer/cart")
	public ModelAndView showCustomerCartView(Principal principal) {
		System.out.println("Reached Customer Show Cart View.");

		ModelAndView model = new ModelAndView("showCustomerCart");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);

		List<ShowCartProduct> cartproductlist = customerdao.getShowCartProductsByUserid(customer.getCustid());
		model.addObject("cartproductlist", cartproductlist);
		
		Double sumtotal = 0.0;
		for(ShowCartProduct pro : cartproductlist)
		{
			sumtotal += (pro.getQuantity()*pro.getMrp());
		}
		model.addObject("totalcost",sumtotal);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);

		return model;
	}
	
	@RequestMapping(value = "/customer/cart/subtract/{productid}", method = RequestMethod.GET)
	public ModelAndView updateCustomerCartProductSubtractView(Principal principal, @PathVariable int productid) 
	{
		System.out.println("Reached Customer Cart subtract ProductView");

		ModelAndView model = new ModelAndView("redirect:/customer/cart");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		customerdao.deleteSingleProductFromCart(customer.getCustid(), productid);
		return model;
	}
	
	@RequestMapping(value = "/customer/cart/add/{productid}", method = RequestMethod.GET)
	public ModelAndView updateCustomerCartProductAddView(Principal principal, @PathVariable int productid) 
	{
		System.out.println("Reached Customer Cart Add ProductView");
		System.out.println("requested for product: " + productid + " : ");

		ModelAndView model = new ModelAndView("redirect:/customer/cart");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		
		customerdao.addSingleProductInCart(customer.getCustid(), productid,1);
		return model;
	}
	
	@RequestMapping(value = "/customer/cart/removeproduct/{productid}", method = RequestMethod.GET)
	public ModelAndView removeWholeProductFromCustomerCartView(Principal principal, @PathVariable int productid) 
	{
		System.out.println("Reached Customer Cart Add ProductView");

//		ModelAndView model = new ModelAndView("redirect:/customer/cart");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		
		customerdao.deleteWholeProductFromCartOfUser(customer.getCustid(), productid);
		
		ModelAndView model = new ModelAndView("showCustomerCart");

		model.addObject("customer", customer);

		List<ShowCartProduct> cartproductlist = customerdao.getShowCartProductsByUserid(customer.getCustid());
		model.addObject("cartproductlist", cartproductlist);
		
		Double sumtotal = 0.0;
		for(ShowCartProduct pro : cartproductlist)
		{
			sumtotal += (pro.getQuantity()*pro.getMrp());
		}
		model.addObject("totalcost",sumtotal);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		model.addObject("msg","Product Successfully removed from cart.");
		model.addObject("colorOfInstruction","green");

		return model;
	}
	
	@RequestMapping(value = "/customer/addproduct/{productid}", method = RequestMethod.POST)
	public ModelAndView addProductToCustomerCartView(Principal principal, @PathVariable int productid, @RequestParam("quantity") Integer counter) 
	{
		System.out.println("Reached addProductToCustomerCartView");

		ModelAndView model = new ModelAndView("redirect:/customer/cart");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		
		customerdao.addSingleProductInCart(customer.getCustid(), productid,counter);
		return model;
	}
	
	@RequestMapping(value="/customer/editprofiledetails", method = RequestMethod.POST)
	public ModelAndView customerProfileUpdaterView(Principal principal, @RequestParam Map<String,String> reqmap ) 
	{
		System.out.println("Reached editprofileView ");
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = new Date(0);
		try {
			parsed = (Date) format.parse(reqmap.get("dob"));
		} catch (Exception e) {
			throw new RuntimeException("");
		}
		java.sql.Date enteredDate = new java.sql.Date(parsed.getTime());
        
        Customer customer = new Customer();
        customer.setCustid(10);
        customer.setCity(reqmap.get("city"));
        customer.setDoornum(reqmap.get("doornum"));
        customer.setGender(reqmap.get("gender"));
        customer.setName(reqmap.get("name"));
        customer.setPhone(reqmap.get("phone"));
        customer.setPincode(reqmap.get("pincode"));
        customer.setState(reqmap.get("state"));
        customer.setStreetname(reqmap.get("streetname"));
        customer.setUname(principal.getName());
        customer.setDob(enteredDate);
		customerdao.updateCustomerInfo(customer);
		
		ModelAndView model = new ModelAndView("consumerViewProfile");
		customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		model.addObject("msg","Information Updated Successfully.");
		model.addObject("colorOfInstruction","green");
		
		return model;
	}
	
	@RequestMapping(path = "/customer/seeprofilepage", method = RequestMethod.GET)
	public ModelAndView consumerSeesProfileView(Principal principal) {
		System.out.println("Reached consumerSeesProfileView");
		ModelAndView model = new ModelAndView("consumerViewProfile");
		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		return model;
	}
	
	@RequestMapping(path = "/customer/editprofilepage", method = RequestMethod.GET)
	public ModelAndView consumerProfileView(Principal principal) {
		System.out.println("Reached consumer Profile view");
		ModelAndView model = new ModelAndView("consumerProfilePage");
		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		return model;
	}
	
	
	@RequestMapping(value = "/customer/enterorderdetails")
	public ModelAndView showCustomerEnterOrderDetailsView(Principal principal) {
		System.out.println("Reached showCustomerEnterOrderDetailsView.");

		ModelAndView model = new ModelAndView("customerenterorderdetails");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);

		List<ShowCartProduct> cartproductlist = customerdao.getShowCartProductsByUserid(customer.getCustid());
		model.addObject("cartproductlist", cartproductlist);
		
		Double sumtotal = 0.0;
		for(ShowCartProduct pro : cartproductlist)
		{
			sumtotal += (pro.getQuantity()*pro.getMrp());
		}
		model.addObject("totalcost",sumtotal);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);

		return model;
	}
	
	
	@RequestMapping(value="/customer/placeorder", method = RequestMethod.POST)
	public ModelAndView customerplaceorder(HttpServletRequest request, Principal principal, @RequestParam Map<String,String> reqmap) {
		
		
		
		Customer customer = customerdao.getUserByUsername( principal.getName());
		
		List<ShowCartProduct> cartproductlist = customerdao.getShowCartProductsByUserid(customer.getCustid());
		
		Double sumtotal = 0.0;
		for(ShowCartProduct pro : cartproductlist)
		{
			sumtotal += (pro.getQuantity()*pro.getMrp());
		}
		
		if(cartproductlist.isEmpty()) 
		{
			/*
			 * ModelAndView model = new
			 * ModelAndView("redirect:/customer/enterorderdetails");
			 * model.addObject("customer",customer);
			 * model.addObject("cartproductlist",cartproductlist);
			 * model.addObject("sumtotal",sumtotal); model.addObject("msg",
			 * "Could not place order. Cart is empty."); return model;
			 */
			
			ModelAndView model = new ModelAndView("customerenterorderdetails");

			customer = customerdao.getUserByUsername(principal.getName());
			model.addObject("customer", customer);

			cartproductlist = customerdao.getShowCartProductsByUserid(customer.getCustid());
			model.addObject("cartproductlist", cartproductlist);
			
			sumtotal = 0.0;
			for(ShowCartProduct pro : cartproductlist)
			{
				sumtotal += (pro.getQuantity()*pro.getMrp());
			}
			model.addObject("totalcost",sumtotal);
			
			int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
			model.addObject("productInCart",productInCart);
			
			model.addObject("msg", "Could not place order. Cart is empty.");
			model.addObject("colorOfInstruction", "red");

			return model;
		}
		
		Order order = new Order();
		order.setCustid(customer.getCustid());
		order.setDeliveringto(reqmap.get("name"));
		order.setDeliverycity(reqmap.get("city"));
		order.setDeliverydoornum(reqmap.get("doornum"));
		order.setDeliveryphone(reqmap.get("phone"));
		order.setDeliverypincode(reqmap.get("pincode"));
		order.setDeliverystate(reqmap.get("state"));
		order.setDeliverystreetname(reqmap.get("streetname"));
		order.setOrderstatus("PENDING");
		order.setPaymentmode("Cash-on-delivery");
		order.setPaymentstatus("PENDING");
		order.setTotalamount(sumtotal);
		order.setAssignedemployeestatus("PENDING");
		
		
		processOrder(order,cartproductlist);
		
		ModelAndView model = new ModelAndView("orderplacedsuccessfully");
		model.addObject("customer",customer);
		int orderid = customerdao.getlastorderbycustid(order.getCustid());
		model.addObject("orderid",orderid);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		return model;
	}
	
	public void processOrder(Order order, List<ShowCartProduct> cartproductlist)
	{
		for(ShowCartProduct showcartproduct : cartproductlist)
		{
			productdao.decreaseProductCountByProductId(showcartproduct.getProductid(),showcartproduct.getQuantity());
		}
		
		for(ShowCartProduct showcartproduct : cartproductlist)
		{
			customerdao.deleteWholeProductFromCartOfUser(order.getCustid(), showcartproduct.getProductid());
		}
		
		orderdao.insertNewOrder(order);
		
		int orderid = customerdao.getlastorderbycustid(order.getCustid());
		
		for(ShowCartProduct showcartproduct : cartproductlist)
		{
			orderdao.insertIntoOrderProduct(orderid,showcartproduct.getProductid(),showcartproduct.getQuantity());
		}
		
		return;
	}
	
	
	@RequestMapping(value = "/customer/orderdetails/{orderid}", method = RequestMethod.GET)
	public ModelAndView customerOrderDetailsView(Principal principal, @PathVariable int orderid) 
	{
		System.out.println("Reached customerOrderDetailsView");

		ModelAndView model = new ModelAndView("customerorderdetails");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		System.out.println("yahan tak chal raha");
		Order order = orderdao.getOrderDetailsByOrderId(orderid);
		
		model.addObject("customer",customer);
		model.addObject("order",order);
		
		System.out.println("yahan tak chal raha");
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		List<ShowCartProduct> showcartproducts = orderdao.getorderproductdetails(orderid);
		
		for(ShowCartProduct showcartproduct : showcartproducts)
		{
			showcartproduct.setProductname(productdao.getProductNameByProductId(showcartproduct.getProductid()));
		}
		
		model.addObject("showcartproducts",showcartproducts);
		return model;
	}
	
	@RequestMapping(value = "/customer/allorderdetails", method = RequestMethod.GET)
	public ModelAndView customerAllOrderDetailsView(Principal principal) 
	{
		System.out.println("Reached customerAllOrderDetailsView");

		ModelAndView model = new ModelAndView("customerallorderdetails");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		List<Order> orders = orderdao.getAllOrdersByCustid(customer.getCustid());
		model.addObject("customer",customer);
		model.addObject("orders",orders);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		return model;
	}
	
	@RequestMapping("/customer/cancelorder/{orderid}")
	public ModelAndView customerCancelOrder(Principal principal, @PathVariable int orderid) 
	{
		Customer customer = customerdao.getUserByUsername( principal.getName());
		
		List<ShowCartProduct> showcartproducts = orderdao.getorderproductdetails(orderid);
		
		for(ShowCartProduct showcartproduct : showcartproducts)
		{
			productdao.editProductAddQuantityByProductId(showcartproduct.getProductid(), showcartproduct.getQuantity());
		}
		
		orderdao.updateorderstatus(orderid, "CANCELLED");
		
		ModelAndView model = new ModelAndView("customerallorderdetails");

		customer = customerdao.getUserByUsername(principal.getName());
		List<Order> orders = orderdao.getAllOrdersByCustid(customer.getCustid());
		model.addObject("customer",customer);
		model.addObject("orders",orders);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		model.addObject("msg", "Order Cancelled Successfully.");
		model.addObject("colorOfInstruction", "green");
		
		return model;
		
	}
	
	
	@RequestMapping(value = "/customer/givefeedback/{orderid}", method = RequestMethod.GET)
	public ModelAndView customerGiveFeedbackView(Principal principal, @PathVariable int orderid) 
	{
		System.out.println("Reached customerGiveFeedbackView");

		ModelAndView model = new ModelAndView("customerfeedbackview");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		Order order = orderdao.getOrderDetailsByOrderId(orderid);
		model.addObject("customer",customer);
		model.addObject("order",order);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		List<ShowCartProduct> showcartproducts = orderdao.getorderproductdetails(orderid);
		
		for(ShowCartProduct showcartproduct : showcartproducts)
		{
			showcartproduct.setProductname(productdao.getProductNameByProductId(showcartproduct.getProductid()));
		}
		
		model.addObject("showcartproducts",showcartproducts);
		return model;
	}
	
	
	@RequestMapping(value = "/customer/givefeedback/{orderid}", method = RequestMethod.POST)
	public ModelAndView saveCustomerGiveFeedbackView(Principal principal, @PathVariable int orderid, @RequestParam Map<String,String> reqmap) 
	{
		System.out.println("Reached saveCustomerGiveFeedbackView");
		
		Customer customer = customerdao.getUserByUsername(principal.getName());
		
		Feedback feedback = new Feedback();
		feedback.setEmployeerating(Integer.parseInt(reqmap.get("employeerating")));
		feedback.setFeedbacktext(reqmap.get("feedbacktext"));
		feedback.setWebsiterating(Integer.parseInt(reqmap.get("websiterating")));
		feedback.setOrderid(orderid);
		feedback.setCustomerid(customer.getCustid());
		
		customerdao.addFeedback(feedback);

		ModelAndView model = new ModelAndView("customerallorderdetails");

		customer = customerdao.getUserByUsername(principal.getName());
		List<Order> orders = orderdao.getAllOrdersByCustid(customer.getCustid());
		model.addObject("customer",customer);
		model.addObject("orders",orders);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		model.addObject("msg", "Your Feedback has been recorded.");
		model.addObject("colorOfInstruction", "green");
		
		return model;
	}
	
	@RequestMapping("/customer/contact")
	public ModelAndView customerContactView(Principal principal) {
		System.out.println("Reached customerContactView");

		ModelAndView model = new ModelAndView("customercontact");

		Customer customer = customerdao.getUserByUsername(principal.getName());
		model.addObject("customer", customer);

		List<Shop> shops = shopdao.getAllShopsDetails();
		model.addObject("shops", shops);
		
		int productInCart = customerdao.tellCustomerTotalProductInCart(customer.getCustid());
		model.addObject("productInCart",productInCart);
		
		return model;
	}
	/*
	 * @RequestMapping("/addproducts") public String addProducts() {
	 * System.out.println("Reached Add Products View"); String desc =
	 * "When used as a post-cleanse toner, Bioderma wipes away remaining traces of toxins "
	 * +
	 * "and impurities from your skin and pores. With ingredients like manna sugar, rhamnose, and "
	 * +
	 * "capric triglycerides (a compound that comes from coconut), it balances skin's pH level and calms irritation."
	 * ;
	 * 
	 * Product product = new Product(0, 50, "Bioderma", desc, "Bioderma", 85,
	 * "images/product_01.png");
	 * 
	 * productdao.addProductByAdmin(product);
	 * 
	 * product.setQtyinstock(100); product.setName("Bioderma02");
	 * product.setImname("images/product_02.png"); product.setMrp(150);
	 * System.out.println(product.toString());
	 * productdao.addProductByAdmin(product);
	 * 
	 * product.setQtyinstock(100); product.setName("Bioderma03");
	 * product.setImname("images/product_03.png"); product.setMrp(250);
	 * productdao.addProductByAdmin(product);
	 * 
	 * product.setQtyinstock(100); product.setName("Bioderma04");
	 * product.setImname("images/product_04.png"); product.setMrp(75);
	 * productdao.addProductByAdmin(product);
	 * 
	 * product.setQtyinstock(100); product.setName("Bioderma05");
	 * product.setImname("images/product_05.png"); product.setMrp(300);
	 * productdao.addProductByAdmin(product);
	 * 
	 * product.setQtyinstock(100); product.setName("Bioderma06");
	 * product.setImname("images/product_06.png");
	 * productdao.addProductByAdmin(product);
	 * 
	 * product.setQtyinstock(100); product.setName("Bioderma07");
	 * product.setImname("images/product_06.png"); product.setMrp(450);
	 * productdao.addProductByAdmin(product);
	 * 
	 * product.setQtyinstock(100); product.setName("Bioderma08");
	 * product.setImname("images/product_02.png"); product.setMrp(555);
	 * productdao.addProductByAdmin(product);
	 * 
	 * System.out.println("Products added successfully");
	 * 
	 * return "home"; }
	 */
}
