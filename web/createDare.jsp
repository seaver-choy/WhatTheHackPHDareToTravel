<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dare To Travel - Create Dare</title>
	</head>
	<script type="text/javascript" src="vendors/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="vendors/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.min.css">
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
				<form method="POST" enctype="multipart/form-data" action="DareServlet" method="post">
                                          <div class ="form-group">
                                              <label for="dareName">Name of Dare:</label> 
                                              <input type ="text" class= "form-control" name ="dareName">
                                          </div>
                                          <div class="form-group">
					  	<label for="description">Description:</label>
					  	<input type="text" class="form-control" name="description">
					  </div>
                                    
                                          <div class ="form-group">
                                              <label for="instructions">Instructions:</label>
                                              <input type ="text" class="form-control" name ="instructions">
                                          </div>
                                    
					  <div class="form-group">
					  	<label for="photoFile">Photo to upload:</label>
					  	<input type="file" name="photoFile">
					  </div>
					  
					  <div class="form-group">
					  	<label for="tags"> Tags:</label>
					  	<input type="text" name="tags">
					  </div>
					  
					  <button type="submit" class="btn btn-default" value="Press">Create Dare</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>