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
<form action="/send-message" method="post">
Receiver Id:<input type="number" name="receiverid"/><br> 
Message:<input type="text" name="description"/>
<input type="submit" value="send"/>
</form>
 ${msg}
</body>
</html>