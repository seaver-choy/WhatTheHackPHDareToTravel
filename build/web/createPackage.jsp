<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wth.model.PackageDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dare To Travel- Create Package</title>
<script type="text/javascript" src="vendors/jquery/jquery.min.js"></script>
<script type="text/javascript" src="vendors/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.min.css">
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
				<br>
			</div>
			<div class="col-md-6">
				<form>
					<div class="list-group">
						<label for="package-name">Package name:</label>
						<input type="text" class="form-control" name="package-name">
					</div>
					<div class="list-group">
						<label for="package-name">Description:</label>
						<textarea class="form-control" name="package-name"></textarea>
					</div>
					<div class="list-group">
						<label for="search-location">Location:</label>
						<input type="text" class="form-control" id="search-location">
					</div>
					<button type="submit" class="form-control">Create Package</button>
				</form>
			</div>
			<div class="col-md-3">
				<br>
			</div>
		</div>
	</div>
</body>
</html>