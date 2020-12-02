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
							<a href="/admin" class="js-logo-clone">Pharma</a>
						</div>
					</div>
					<div class="main-nav d-none d-lg-block">
						<nav class="site-navigation text-right text-md-center"
							role="navigation">
							<ul class="site-menu js-clone-nav d-none d-lg-block">
								<li><a href="/admin">Home</a></li>
								<!-- <li class="active"><a href="/admin">Store</a></li> -->
								<li><a href="/admin/contact">Contact Us</a></li>
								<li class="has-children"><a href="/admin">${admin.name}</a>
									<ul class="dropdown">
										<li><a href="/admin/seeprofilepage">Manage Your Profile</a></li>
										<li class="has-children"><a href="#">Add</a>
											<ul class="dropdown">
												<li><a href="/admin/addnewadmin">Admin</a></li>
												<li><a href="/admin/addnewemployee">Employee</a></li>
												<li><a href="/admin/addnewproduct">Product</a></li>
												<li><a href="/admin/addnewsupplier">Supplier</a></li>
												<li><a href="/admin/addnewshop">Shop</a></li>
											</ul></li>
										<li class="has-children"><a href="#">See Details</a>
											<ul class="dropdown">
												<li><a href="/admin/customerdetails">Customers</a></li>
												<li><a href="/admin/employeedetails">Employees</a></li>
												<li><a href="/admin/admindetails">Admins</a></li>
												<li><a href="/admin/orderdetails">Orders</a></li>
												<li><a href="/admin/productdetails">Product</a></li>
												<li><a href="/admin/supplierdetails">Suppliers</a></li>
												<li><a href="/admin/feedbackdetails">Feedbacks</a></li>
												<li><a href="/admin/shopdetails">Shops</a></li>
											</ul></li>
										<li><a href="/j_spring_security_logout">Log Out</a></li>

									</ul></li>
							</ul>
						</nav>
					</div>
					<div class="icons">
						<a href="/admin/orderdetails" class="icons-btn d-inline-block bag"> <span
							class="icon-shopping-bag"></span> <span class="number">${unassignedorder }</span>
						</a> <a href="/admin/orderdetails"
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
						<a href="/admin">Home</a> <span class="mx-2 mb-0">/</span> <strong
							class="text-black">Product Details</strong>
					</div>
				</div>
			</div>
		</div>
		
		<br>
		<p style="text-align:center; color:${colorOfInstruction} !important;">${msg}</p>

		<div class="site-section">
			<div class="container">
				<div class="row mb-5">
						<div class="site-blocks-table">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Product ID</th>
										<th class="product-thumbnail">Image</th>
										<th class="product-name">Product Name</th>
										<th class="product-price">M.R.P</th>
										<th class="product-quantity">Quantity In stock</th>
										<!-- <th class="product-total">Total</th> -->
										<!-- <th class="product-remove">Email ID</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${products }" var="product">
										<tr>
											<td>${product.id }</td>
											<td>
												<img src="/${product.imname }" alt="Image" class="img-fluid">
											</td>
											<td><a href = "/admin/viewproductinfo/${product.id }">${product.name }</a></td>
											<td>${product.mrp }</td>
											<td>${product.qtyinstock }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
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
							<p>Our company Pharma was founded to offer an unparalleled customer service and patient care. 
							What started as a single location in 2009 has grown to six locations, making it the fastest 
							growing independent pharmacy in Philadelphia.</p>
						</div>

					</div>
					<div class="col-lg-3 mx-auto mb-5 mb-lg-0">
						<h3 class="footer-heading mb-4">Quick Links</h3>
						<ul class="list-unstyled">
							<li><a href="/admin">Supplements</a></li>
							<li><a href="/admin">Vitamins</a></li>
							<li><a href="/admin">Diet &amp; Nutrition</a></li>
							<li><a href="/admin">Medicines</a></li>
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