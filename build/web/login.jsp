<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dare To Travel - Login</title>
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
				<li><a href="login.jsp">Login</a></li>
				<li><a href="register.jsp">Register</a></li>
			  </ul>
			</div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" action="LoginServlet" method="post" >
					<div class="form-group">
						<label  class="control-label col-md-2" for="username">Username:</label>
						<div class="col-md-3">
							<input type="text" class="form-control" name="username" id="username">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2" for="password">Password:</label>
						<div class="col-md-3">
							<input type="password" class="form-control" name="password" id="password">
						</div>
					</div>
					<div class="col-md-offset-2">
						<button type="submit" class="btn btn-default"> Login </button>
						<a class="btn btn-danger" href="index.jsp"> Cancel </a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>