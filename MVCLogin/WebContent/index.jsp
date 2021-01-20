<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
</head>
<body>
	<form action="<%= request.getContextPath() %>/Login" method="post">  
    	<p>Username: <input type="text" name="username"/></p>
    	<p>Password: <input type="password" name="password"/></p>
    	<input type="submit" value="Login"/>
    </form>
</body>
</html>