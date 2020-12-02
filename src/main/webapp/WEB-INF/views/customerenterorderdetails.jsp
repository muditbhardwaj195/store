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

<style>
DIV.table {
	display: table;
}

FORM.tr, DIV.tr {
	display: table-row;
}

SPAN.td {
	display: table-cell;
}
</style>

</head>

<body>

	<div class="site-wrap">
		<form action="/customer/placeorder" method="POST">
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
										<li><a href="/customer/seeprofilepage">Manage Your Profile</a></li>
										<!-- <li class="has-children"><a href="#">Vitamins</a>
											<ul class="dropdown">
												<li><a href="#">Supplements</a></li>
												<li><a href="#">Vitamins</a></li>
												<li><a href="#">Diet &amp; Nutrition</a></li>
												<li><a href="#">Tea &amp; Coffee</a></li>
											</ul></li> -->
										<li><a href="/customer/cart">Manage Your Cart</a></li>
										<li><a href="/customer/allorderdetails">Manage Your Orders</a></li>
										<li><a href="/j_spring_security_logout">Log Out</a></li>

									</ul></li>
							</ul>
						</nav>
					</div>
					<div class="icons">
						<a href="/customer/cart" class="icons-btn d-inline-block bag"> <span
							class="icon-shopping-bag"></span> <span class="number">${productInCart }</span>
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
								class="text-black">Enter Order Details</strong>
						</div>
					</div>
				</div>
			</div>
			
			<br>
		<p style="text-align:center; color:${colorOfInstruction} !important;">${msg}</p>

			<div class="site-section">
				<div class="container">
					<div class="row">
						<div class="col-md-6 mb-5 mb-md-0">
							<h2 class="h3 mb-3 text-black">Billing Details</h2>
							<div class="p-3 p-lg-5 border">
								<!-- <div class="form-group">
                <label for="c_country" class="text-black">Country <span class="text-danger">*</span></label>
                <select id="c_country" class="form-control">
                  <option value="1">Select a country</option>
                  <option value="2">bangladesh</option>
                  <option value="3">Algeria</option>
                  <option value="4">Afghanistan</option>
                  <option value="5">Ghana</option>
                  <option value="6">Albania</option>
                  <option value="7">Bahrain</option>
                  <option value="8">Colombia</option>
                  <option value="9">Dominican Republic</option>
                </select>
              </div> -->
								<div class="form-group row">
									<div class="col-md-12">
										<label for="c_fname" class="text-black">Recipient Name
											<span class="text-danger">*</span>
										</label> <input type="text" class="form-control" id="name" name="name"
											required="required" value=${customer.name }>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-6">
										<label for="c_state_country" class="text-black">Door
											Number <span class="text-danger">*</span>
										</label> <input type="text" class="form-control" id="doornum"
											name="doornum" required="required" value=${customer.doornum }>
									</div>
									<div class="col-md-6">
										<label for="c_postal_zip" class="text-black">Streetname<span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="streetname" name="streetname"
											required="required" value=${customer.streetname }>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-6">
										<label for="c_state_country" class="text-black">City <span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="city" name="city"
											required="required" value=${customer.city }>
									</div>
									<div class="col-md-6">
										<label for="c_postal_zip" class="text-black">State<span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="state" name="state"
											required="required" value=${customer.state }>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-6">
										<label for="c_state_country" class="text-black">Pincode
											<span class="text-danger">*</span>
										</label> <input type="text" class="form-control" id="pincode"
											name="pincode" required="required" value=${customer.pincode }>
									</div>
									<div class="col-md-6">
										<label for="c_postal_zip" class="text-black">Phone
											Number<span class="text-danger">*</span>
										</label> <input type="text" class="form-control" id="phone"
											name="phone" required="required" value=${customer.phone }>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-md-12">
										<label for="c_address" class="text-black">Email <span
											class="text-danger">*</span></label> <input type="text"
											class="form-control" id="email" name="email"
											required="required" value=${customer.email }>
									</div>
								</div>

								<div class="form-group">
									<label for="c_order_notes" class="text-black">Order
										Notes</label>
									<textarea name="c_order_notes" id="notes" cols="30" rows="5"
										class="form-control" placeholder="Write your notes here..."></textarea>
								</div>

							</div>
						</div>
						<div class="col-md-6">

							<!-- <div class="row mb-5">
              <div class="col-md-12">
                <h2 class="h3 mb-3 text-black">Coupon Code</h2>
                <div class="p-3 p-lg-5 border">
    
                  <label for="c_code" class="text-black mb-3">Enter your coupon code if you have one</label>
                  <div class="input-group w-75">
                    <input type="text" class="form-control" id="c_code" placeholder="Coupon Code" aria-label="Coupon Code"
                      aria-describedby="button-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary btn-sm px-4" type="button" id="button-addon2">Apply</button>
                    </div>
                  </div>
    
                </div>
              </div>
            </div> -->

							<div class="row mb-5">
								<div class="col-md-12">
									<h2 class="h3 mb-3 text-black">Your Order</h2>
									<div class="p-3 p-lg-5 border">
										<table class="table site-block-order-table mb-5">
											<thead>
												<th>Product</th>
												<th>Total</th>
											</thead>
											<tbody>

												<c:forEach items="${cartproductlist }" var="cartproduct">
													<tr>
														<td>${cartproduct.productname }<strong class="mx-2">x</strong>
															${cartproduct.quantity }
														</td>
														<td>${cartproduct.quantity * cartproduct.mrp }</td>
													</tr>
												</c:forEach>
												<tr>
													<td class="text-black font-weight-bold"><strong>Order
															Total</strong></td>
													<td class="text-black font-weight-bold"><strong>${totalcost }</strong></td>
												</tr>
											</tbody>
										</table>

										<div class="border mb-5">
											<h3 class="h6 mb-0">
												<a class="d-block" data-toggle="collapse"
													href="#collapsepaypal" role="button" aria-expanded="false"
													aria-controls="collapsepaypal">Cash On Delivery</a>
											</h3>

											<div class="collapse" id="collapsepaypal">
												<div class="py-2 px-4">
													<p class="mb-0">Make your payment directly to our
														employee by cash when your product is delivered to your
														doorsteps.</p>
												</div>
											</div>
										</div>

										<div class="form-group">
											<input type = "submit" class="btn btn-primary btn-lg btn-block" value = "Place Order">
										</div>

									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- </form> -->
				</div>
		</form>
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