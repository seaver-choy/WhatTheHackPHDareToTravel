<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wth.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dare To Travel- View Dare</title>
<script type="text/javascript" src="vendors/jquery/jquery.min.js"></script>
<script type="text/javascript" src="vendors/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/js/viewDare.js"></script>
	  <style>
	  .carousel-inner > .item > img,
	  .carousel-inner > .item > a > img {
	  	  height: 50%;
	      width: 70%;
	      margin: auto;
	  }
	  .empty{
	  		background-color: green;
	   }
	  .max{
		max-width:200px;
		max-height:300px;
		margin-left:125px;
		}
		
		.main-row{
			background-color: maroon;
			color: blue;
		}
		
		.mainrow-text{
			color:white;
		}
		.col-md-5 {
			border-color: gray;
			border-width: 10px;
		}
		
		h1 {
			font-size: 2em;
		}
	  </style>
</head>
<body>
	<%
		String dareID = request.getParameter("id");
		DareDAO dareDAO = new MySQLDareDAO();
		Dare dare = dareDAO.getDare(dareID);
		int locID = dare.getLocID();
		LocationDAO locDAO = new MySQLLocationDAO();
		Location loc = locDAO.getLocation(locID);
		session.setAttribute("dare_id", dareID);
		boolean isChallengeCompleted = false;
		AccountDAO acctDAO = new MySQLAccountDAO();
		Account acct = acctDAO.getAccount((String) session.getAttribute("username"));
		if(acct != null){
			isChallengeCompleted = dareDAO.isCompleted(acct, dare);
		}
	%>
	
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
			<div class="col-md-3 empty">
				<br> hahah
			</div>
			<div class="col-md-6">
				<h1 class="text-center"><b><%= dare.getDareName() %></b></h1>
				<br>
				<img class="max" src="resources/images/dare_cover.jpg" align="middle">
			</div>
			<div class="col-md-3 empty">
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 empty">
				<br>
			</div>
			<div class="col-md-6">
				<ul class="list-group">
					<li class="list-group main-row" id="instructions-label"><h3 class="text-center mainrow-text">Instructions</h3></li>
					<div class="row" id ="instructions-row">
						<div class="col-md-12">
							<p><%= dare.getDescription() %></p>
						</div>
					</div>
					<li class="list-group main-row" id="location-label"><h3 class="text-center mainrow-text">Location</h3></li>
					<div class="row" id ="location-row">
						<div class="col-md-12">
							<h4>Address</h4>
							<p><%= loc.getAddress() %></p>
						</div>
					</div>
					<li class="list-group main-row" id="submission-label"><h3 class="text-center mainrow-text">Submission</h3></li>
					<div class ="row" id ="submission-row">
						<div class="col-md-12">
							<% if(session.getAttribute("username") == null){ %>
							<a href = "login.jsp"><button>Login first!</button></a>
							<%} else if(!isChallengeCompleted){%>
							<form method="POST" action = "viewDareServlet" enctype="multipart/form-data">
								<div class="form-group">
						  			<label for="photoFile">Photo to submit:</label>
						  			<input type="file" name="photoFile">
						  		</div>
								<div class="form-group">
						  			<input type="submit" name="photoFile">
						  		</div>
							</form>
							<% }else{ %>
								<div>CHALLENGE COMPLETED</div>
							<% } %>
						</div>
					</div>
				</ul>
			</div>
			<div class="col-md-3 empty">
				<br>
			</div>
		</div>
							
	</div>
</body>
</html>