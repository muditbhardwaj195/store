<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <title>Pharma</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link href="https://fonts.googleapis.com/css?family=Rubik:400,700|Crimson+Text:400,400i" rel="stylesheet">
  <link rel="stylesheet" href="fonts/icomoon/style.css">

  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/magnific-popup.css">
  <link rel="stylesheet" href="css/jquery-ui.css">
  <link rel="stylesheet" href="css/owl.carousel.min.css">
  <link rel="stylesheet" href="css/owl.theme.default.min.css">


  <link rel="stylesheet" href="css/aos.css">

  <link rel="stylesheet" href="css/style.css">

</head>

<body>

  <div class="site-wrap">


    <div class="site-navbar py-2">

			<div class="container">
				<div class="d-flex align-items-center justify-content-between">
					<div class="logo">
						<div class="site-logo">
							<a href="/home" class="js-logo-clone">Pharma</a>
						</div>
					</div>
					<div class="icons">
						<div class="main-nav d-none d-lg-block">
							<nav class="site-navigation text-right text-md-center"
								role="navigation">
								<ul class="site-menu js-clone-nav d-none d-lg-block">
									<li><a href="/login">Log In</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>

    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0">
            <a href="/home">Home</a> <span class="mx-2 mb-0">/</span>
            <strong class="text-black">Register</strong>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h2 class="h3 mb-5 text-black text-center">Register</h2>
            <p style="text-align:center; color:red !important;">${msg}</p>
          </div>
          <div class="col-md-12">
    
            <form:form action="/signup" modelAttribute="user" method="POST">
    
              <div class="p-3 p-lg-5 border">
                <div class="form-group row">
                  <div class="col-md-6">
                    <label for="c_fname" class="text-black">Name <span class="text-danger">*</span></label>
                    <form:input type="text" path="name" class="form-control" id="name" name="name" required="required" />
                    <form:errors path = "name" style="text-align:center; color:red !important;" />
                  </div>
                  <div class="col-md-6">
                    <label for="c_lname" class="text-black">Mobile Number <span class="text-danger">*</span></label>
                    <form:input type="text" path="phone" pattern="\d{10}" class="form-control" id="phone" name="phone" required="required"/>
                    <form:errors path = "phone" style="text-align:center; color:red !important;" />
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_email" class="text-black">Email <span class="text-danger">*</span></label>
                    <form:input type="email" path="email" class="form-control" id="email" name="email" placeholder="" required="required"/>
                    <form:errors path = "email" style="text-align:center; color:red !important;" />
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_subject" class="text-black">Username </label>
                    <form:input type="text" path="username" class="form-control" id="username" name="username" required="required"/>
                    <form:errors path = "username"  style="text-align:center; color:red !important;" />
                  </div>
                </div>
                
                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_subject" class="text-black">Password </label>
                    <form:input type="password" path="password" class="form-control" id="password" name="password" required="required"/>
                    <form:errors path = "password"  style="text-align:center; color:red !important;" />
                  </div>
                </div>
                
                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_subject" class="text-black">Confirm Password </label>
                    <form:input type="password" path="repassword" class="form-control" id="repassword" name="repassword" required="required"/>
                    <form:errors path = "repassword" style="text-align:center; color:red !important;" />
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-lg-12">
                    <input type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
                  </div>
                </div>
              </div>
            </form:form>
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
							<li><a href="/login">Supplements</a></li>
							<li><a href="/login">Vitamins</a></li>
							<li><a href="/login">Diet &amp; Nutrition</a></li>
							<li><a href="/login">Medicines</a></li>
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

  <script src="js/jquery-3.3.1.min.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>

  <script src="js/main.js"></script>

</body>

</html>