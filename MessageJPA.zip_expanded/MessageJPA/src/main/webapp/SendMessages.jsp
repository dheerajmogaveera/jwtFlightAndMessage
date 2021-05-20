<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Send Messages Page</h1>
<form action="/login" method="post">
MobileNumber:<input type="text" name="mobileNumber"/><br> 
Message:<input type="text" name="message"/>
<input type="submit" value="send"/>
</form>
</body>
</html>