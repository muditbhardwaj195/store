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
							<a href="/customer" class="js-logo-clone">Pharma</a>
						</div>
					</div>
					<div class="main-nav d-none d-lg-block">
						<nav class="site-navigation text-right text-md-center"
							role="navigation">
							<ul class="site-menu js-clone-nav d-none d-lg-block">
								<li><a href="/customer">Home</a></li>
								<!-- 								<li class="active"><a href="/customer">Store</a></li> -->
								<li><a href="/customer/contact">Contact Us</a></li>
								<li class="has-children"><a href="/customer">${customer.name}</a>
									<ul class="dropdown">
										<li><a href="/customer/seeprofilepage">Manage Your
												Profile</a></li>
										<!-- <li class="has-children"><a href="#">Vitamins</a>
											<ul class="dropdown">
												<li><a href="#">Supplements</a></li>
												<li><a href="#">Vitamins</a></li>
												<li><a href="#">Diet &amp; Nutrition</a></li>
												<li><a href="#">Tea &amp; Coffee</a></li>
											</ul></li> -->
										<li><a href="/customer/cart">Manage Your Cart</a></li>
										<li><a href="/customer/allorderdetails">Manage Your
												Orders</a></li>
										<li><a href="/j_spring_security_logout">Log Out</a></li>

									</ul></li>
							</ul>
						</nav>
					</div>
					<div class="icons">
						<a href="/customer/cart" class="icons-btn d-inline-block bag">
							<span class="icon-shopping-bag"></span> <span class="number">${productInCart }</span>
						</a> <a href="/customer/cart"
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
						<a href="/customer">Home</a> <span class="mx-2 mb-0">/</span> <strong
							class="text-black">Order Details</strong>
					</div>
				</div>
			</div>
		</div>

		<br>
		<p style="text-align:center; color:${colorOfInstruction} !important;">${msg}</p>

		<div class="site-section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="text-black">${customer.name }</h2>

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
									<c:set var="status" value="PENDING" />
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
											<tr>
												<td>Assigned Employee</td>
												<c:if test="${order.assignedemployeestatus == status }">
													<td class="bg-light">Not assigned yet.</td>
												</c:if>
												<c:if test="${order.assignedemployeestatus != status }">
													<td class="bg-light"><a href="#">${order.assignedemployeeid }</a>
													</td>
												</c:if>
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
										Purchased</a></li>

							</ul>
							<div class="tab-content" id="pills-tabContent">
								<div class="tab-pane fade show active" id="pills-profile"
									role="tabpanel" aria-labelledby="pills-profile-tab">

									<table class="table custom-table">

										<tbody>
											<tr>
												<td>Product Name</td>
												<td class="bg-light">Quantity Bought</td>
											</tr>
											<c:forEach items="${showcartproducts }" var="cartproduct">
												<tr>
													<td>${cartproduct.productname }</td>
													<td class="bg-light">${cartproduct.quantity }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>

							</div>
						</div>
						<br>
						<c:set var="orderstatusdelivered" value="DELIVERED" />
						<form action="/customer/givefeedback/${order.orderid}" method="GET">
							<div class="form-group row">
								<div class="col-lg-12">
									<c:if test="${order.orderstatus == orderstatusdelivered}">
										<input type="submit" class="btn btn-primary btn-lg btn-block"
										value="Give Feedback">
									</c:if>
									
								</div>
							</div>
							</form>
					</div>
				</div>
				
			</div>
		</div>




		<footer class="site-footer">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-lg-3 mb-4 mb-lg-0">

						<div class="block-7">
							<h3 class="footer-heading mb-4">About Us</h3>
							<p>Our company Pharma was founded to offer an unparalleled
								customer service and patient care. What started as a single
								location in 2009 has grown to six locations, making it the
								fastest growing independent pharmacy in Philadelphia.</p>
						</div>

					</div>
					<div class="col-lg-3 mx-auto mb-5 mb-lg-0">
						<h3 class="footer-heading mb-4">Quick Links</h3>
						<ul class="list-unstyled">
							<li><a href="/customer">Supplements</a></li>
							<li><a href="/customer">Vitamins</a></li>
							<li><a href="/customer">Diet &amp; Nutrition</a></li>
							<li><a href="/customer">Medicines</a></li>
						</ul>
					</div>

					<div class="col-md-6 col-lg-3">
						<div class="block-5 mb-5">
							<h3 class="footer-heading mb-4">Contact Info</h3>
							<ul class="list-unstyled">
								<li class="address">Malviya Marg, Near, Ansari Rd,
									Chauraha, Bulandshahr, Uttar Pradesh 203001</li>
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