<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dare To Travel</title>
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
			<div class="col-md-12"> 
				<form action="SearchTagServlet" method="post">
					<div class="form-group">
						<label for="complete-field">What are you looking for?</label>
						<input type="text" size="40" class="form-control" id="complete-field" name="tagName">
						<input type="hidden" name="isStart" value="true">
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
					<% if (session.getAttribute("username") == null) { %>
					<p> About blah blah </p>
					<% } else { %>
						<ul class="list-group">
							<li class="list-group-item">
								<h3>Create Race!</h3>
								<p>Make your own package from existing locations!</p>
							</li>
							<li class="list-group-item">
                                                                <a href="createDare.jsp"><h3>Create Dare</h3></a>
								<p>Make your own dare from existing locations!</p>
							</li>
                                                        <li class="list-group-item">
                                                            <a href="viewMyDares.jsp"><h3>View My Challenges></h3></a>
                                                            <p>What challenges have you made?</p>
                                                        </li>
                                                        
						</ul>
					<% } %>
			</div>
		</div>
	</div>
</body>
</html>