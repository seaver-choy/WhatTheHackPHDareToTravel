<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wth.model.Dare,com.wth.model.DareDAO,
    com.wth.model.MySQLDareDAO, com.wth.model.Tag, 
   	java.util.List, java.util.ArrayList"%>
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
	<%
		String filterString = (String)request.getAttribute("filterString");
		String filters[] = filterString.split("_");
		DareDAO dareDAO = new MySQLDareDAO();
		List<Tag> tagList = new ArrayList<>();
		for (String x : filters) {
			tagList.add(new Tag(x));
		}
		Dare[] dares = dareDAO.searchByTag(tagList.toArray(new Tag[tagList.size()]));
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
			<div class="col-md-12">
				<h2><%= filterString %></h2>
				<form action="SearchTagServlet?filterString=<%= filterString %>" method="post">
					<div class="form-group">
						<label for="complete-field">What are you looking for?</label>
						<input type="text" size="40" class="form-control" id="complete-field" name="tagName">
						<input type="hidden" name="isStart" value="false">
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<ul class="list-group">
					<!--  <li class="list-group-item">
						<h3>The Seaman Challenge</h3>
						<p> Challenges for people who love the sea </p>
					</li>
					<li class="list-group-item">
						<h3>Adrenaline Rush Chllenge</h3>
						<p>For all the adrenaline lovers out there </p>
					</li>
					<li class="list-group-item">
						<h3>Restaurant Challenge</h3>
						<p>Taste them all</p>
					</li> -->
					<% for (Dare x : dares) { %>
						<li class="list-group-item"> 
							<a href="viewDare.jsp?id=<%=x.getDareID()%>">
								<h3><%= x.getDareName() %></h3>
								<p><%= x.getDescription() %></p>
							</a>
						</li>
					<% } %>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>