<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dare To Travel - View Package</title>
<script type="text/javascript" src="vendors/jquery/jquery.min.js"></script>
<script type="text/javascript" src="vendors/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.min.css">
	  <style>
	  .carousel-inner > .item > img,
	  .carousel-inner > .item > a > img {
	  	  height: 50%;
	      width: 70%;
	      margin: auto;
	  }
	  </style>
</head>
<body>
	<nav class="navbar navbar-default">
		  <div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
			  <a class="navbar-brand" href="index.jsp">Dare To Travel</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			  <ul class="nav navbar-nav navbar-right">
			  	<% if (session.getAttribute("username") ==  null) { %>
				<li><a href="login.jsp">Login</a></li>
				<li><a href="register.jsp">Register</a></li>
				<% }  else { %>
						<li><a href="LogoutServlet">Logout</a></li>
				<% } %>
			  </ul>
			</div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<ul class="list-group">
					<li class="list-group-item">Location1</li>
					<li class="list-group-item">Location2</li>
					<li class="list-group-item">Location3</li>
				</ul>
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-12">
						<h2>Location1</h2>
						  <div id="myCarousel" class="carousel slide" data-ride="carousel">
						    <!-- Indicators -->
						    <ol class="carousel-indicators">
						      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						      <li data-target="#myCarousel" data-slide-to="1"></li>
						      <li data-target="#myCarousel" data-slide-to="2"></li>
						      <li data-target="#myCarousel" data-slide-to="3"></li>
						    </ol>
						
						    <!-- Wrapper for slides -->
						    <div class="carousel-inner" role="listbox">
						      <div class="item active">
						        <img src="img_chania.jpg" alt="Chania" width="460" height="345">
						      </div>
						
						      <div class="item">
						        <img src="img_chania2.jpg" alt="Chania" width="460" height="345">
						      </div>
						    
						      <div class="item">
						        <img src="img_flower.jpg" alt="Flower" width="460" height="345">
						      </div>
						
						      <div class="item">
						        <img src="img_flower2.jpg" alt="Flower" width="460" height="345">
						      </div>
						    </div>
						
						    <!-- Left and right controls -->
						    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						      <span class="sr-only">Previous</span>
						    </a>
						    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						      <span class="sr-only">Next</span>
						    </a>
						  </div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h4>About</h4>
						<p>put about here etc</p>
						<h4>How to get there</h4>
						<p>How to get there etc</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>