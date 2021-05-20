<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<title>Welcome To Login Page</title>
</head>
<body>	
<nav class="navbar navbar-default">
		<a class="navbar-brand" href="#">Flight Booking System</a>
		<div class="container-fluid">

			<ul class="nav navbar-nav navbar-right">
				<li><a href="/register"><span class="glyphicon glyphicon-user"></span>&nbsp;  Sign Up</a></li>

			</ul>
			
		</div>
	</nav>		
		<div class="container mb-3">
			
		<h3 class="text-center header">Login Page</h3>

		<form class="form form-horizontal" method="post">

			<div class="form-group">
				<label class="control-label col-sm-2">User Name</label>
				<div class="col-sm-10">
					<input type="text" name="username" class="form-control">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2">Password</label>
				<div class="col-sm-10">
					<input type="password" name="password" class="form-control">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Login</button>
					<button type="reset" class="btn btn-default">Clear</button>
				</div>
			</div>

		</form>

	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>
