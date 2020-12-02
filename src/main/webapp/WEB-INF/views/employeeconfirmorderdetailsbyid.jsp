<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Pharma</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Rubik:400,700|Crimson+Text:400,400i"
	rel="stylesheet">
<link rel="stylesheet" href="/fonts/icomoon/style.css">

<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/magnific-popup.css">
<link rel="stylesheet" href="/css/jquery-ui.css">
<link rel="stylesheet" href="/css/owl.carousel.min.css">
<link rel="stylesheet" href="/css/owl.theme.default.min.css">


<link rel="stylesheet" href="/css/aos.css">

<link rel="stylesheet" href="/css/style.css">


</head>

<body>

	<div class="site-wrap">


		<div class="site-navbar py-2">

			<div class="container">
				<div class="d-flex align-items-center justify-content-between">
					<div class="logo">
						<div class="site-logo">
							<a href="/employee" class="js-logo-clone">Pharma</a>
						</div>
					</div>
					<div class="main-nav d-none d-lg-block">
						<nav class="site-navigation text-right text-md-center"
							role="navigation">
							<ul class="site-menu js-clone-nav d-none d-lg-block">
								<li><a href="/employee">Home</a></li>
								<li><a href="/employee/contact">Contact Us</a></li>
								<li class="has-children"><a href="/employee">${employee.name}</a>
									<ul class="dropdown">
										<li><a href="/employee/profilepage">Manage Your Profile</a></li>
										<li><a href="/employee/allorderassignedtoemployee">All Assigned Orders</a></li>
										<li><a href="/j_spring_security_logout">Log Out</a></li>

									</ul></li>
							</ul>
						</nav>
					</div>
					<div class="icons">
						<a href="/employee" class="icons-btn d-inline-block bag"> <span
							class="icon-shopping-bag"></span> <span class="number">${unDeliveredOrders }</span>
						</a> <a href="/employee"
							class="site-menu-toggle js-menu-toggle ml-3 d-inline-block d-lg-none"><span
							class="icon-menu"></span></a>
					</div>
				</div>
			</div>
		</div>

		<div class="bg-light py-3">
			<div class="container">
				<div class="row">
					<div class="col-md-12 mb-0">
						<a href="/employee">Home</a> <span class="mx-2 mb-0">/</span><strong
							class="text-black">Order Details of ID ${order.orderid }</strong>
					</div>
				</div>
			</div>
		</div>
		<div class="site-section">
			<form action = "/employee/markorderdelivered/${order.orderid }" method = "POST">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="text-black">${employee.name }</h2>

						<div class="mt-5">
							<ul class="nav nav-pills mb-3 custom-pill" id="pills-tab"
								role="tablist">
								<li class="nav-item"><a class="nav-link"
									id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
									role="tab" aria-controls="pills-profile" aria-selected="false">Order
										Details</a></li>

							</ul>
							<div class="tab-content" id="pills-tabContent">
								<div class="tab-pane fade show active" id="pills-profile"
									role="tabpanel" aria-labelledby="pills-profile-tab">
									<c:set var="status" value="PENDING"/>
									<table class="table custom-table">
										<tbody>
											<tr>
												<td>Order ID</td>
												<td class="bg-light">${order.orderid }</td>
											</tr>
											<tr>
												<td>Payment Mode</td>
												<td class="bg-light">${order.paymentmode }</td>
											</tr>
											<tr>
												<td>Total Amount</td>
												<td class="bg-light">${order.totalamount }</td>
											</tr>
											<tr>
												<td>Delivery Doornum</td>
												<td class="bg-light">${order.deliverydoornum }</td>
											</tr>
											<tr>
												<td>Streetname</td>
												<td class="bg-light">${order.deliverystreetname }</td>
											</tr>
											<tr>
												<td>City</td>
												<td class="bg-light">${order.deliverycity }</td>
											</tr>
											<tr>
												<td>State</td>
												<td class="bg-light">${order.deliverystate }</td>
											</tr>
											<tr>
												<td>Pincode</td>
												<td class="bg-light">${order.deliverypincode }</td>
											</tr>
											<tr>
												<td>Phone No.</td>
												<td class="bg-light">${order.deliveryphone }</td>
											</tr>
											<tr>
												<td>Deliveringto</td>
												<td class="bg-light">${order.deliveringto }</td>
											</tr>
											<tr>
												<td>Order Placed on</td>
												<td class="bg-light">${order.orderdatetime }</td>
											</tr>
											<tr>
												<td>Payment Status</td>
												<td class="bg-light">${order.paymentstatus }</td>
											</tr>
										</tbody>
									</table>

								</div>

							</div>
						</div>


						<div class="mt-5">
							<ul class="nav nav-pills mb-3 custom-pill" id="pills-tab"
								role="tablist">
								<li class="nav-item"><a class="nav-link"
									id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
									role="tab" aria-controls="pills-profile" aria-selected="false">Products
										Delivered</a></li>

							</ul>
							<div class="tab-content" id="pills-tabContent">
								<div class="tab-pane fade show active" id="pills-profile"
									role="tabpanel" aria-labelledby="pills-profile-tab">

									<table class="table custom-table">

										<tbody>
											<tr>
												<td>Select Items to confirm</td>
												<td>Product Name</td>
												<td class="bg-light">Quantity Bought</td>
											</tr>
											<c:forEach items="${showcartproducts }" var="cartproduct">
												<tr>
													<td>
														 <input type="checkbox" name="" value="" required = "required">
													</td>
													<td>${cartproduct.productname }</td>
													<td class="bg-light">${cartproduct.quantity }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>
								<br>
								<hr>
								<input type="checkbox" name="" value="" required = "required">  I herby confirm that all these products are delivered.<br>
								<br>
								<div class = "text-center">
									<input class="btn btn-outline-success center" type="submit" value="Mark as Delivered">
								</div>
								
							</div>
							
						</div>

					</div>
				</div>
				</form>
			</div>
		</div>


		<footer class="site-footer">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-lg-3 mb-4 mb-lg-0">

						<div class="block-7">
							<h3 class="footer-heading mb-4">About Us</h3>
							<p>Our company Pharma was founded to offer an unparalleled customer service and patient care. 
							What started as a single location in 2009 has grown to six locations, making it the fastest 
							growing independent pharmacy in Philadelphia.</p>
						</div>

					</div>
					<div class="col-lg-3 mx-auto mb-5 mb-lg-0">
						<h3 class="footer-heading mb-4">Quick Links</h3>
						<ul class="list-unstyled">
							<li><a href="/employee">Supplements</a></li>
							<li><a href="/employee">Vitamins</a></li>
							<li><a href="/employee">Diet &amp; Nutrition</a></li>
							<li><a href="/employee">Medicines</a></li>
						</ul>
					</div>

					<div class="col-md-6 col-lg-3">
						<div class="block-5 mb-5">
							<h3 class="footer-heading mb-4">Contact Info</h3>
							<ul class="list-unstyled">
								<li class="address">Malviya Marg, Near, Ansari Rd, Chauraha, Bulandshahr, 
								Uttar Pradesh 203001</li>
								<li class="phone"><a href="tel://23923929210">+82794XXXXXX</a></li>
								<li class="email">agarwalpharma@gmail.com</li>
							</ul>
						</div>


					</div>
				</div>
			</div>
		</footer>
	</div>

	<script src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/jquery-ui.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/owl.carousel.min.js"></script>
	<script src="/js/jquery.magnific-popup.min.js"></script>
	<script src="/js/aos.js"></script>

	<script src="/js/main.js"></script>

</body>

</html>