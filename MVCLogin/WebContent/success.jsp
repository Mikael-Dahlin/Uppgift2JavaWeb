<%@page import="beans.UserBean"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome page</title>
</head>
<body>  
<p>You are successfully logged in!</p>  
<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

  // Check to see if the session has a user bean.
  if(session.getAttribute("user") == null){
	response.sendRedirect("index.jsp");
  } else {
	  UserBean bean = (UserBean) session.getAttribute("user");
	  Boolean timedOut = true;
	  
	  // Check if the cookie has expired.
	  for(Cookie ck: request.getCookies()) if(bean.getName().equals(ck.getValue())) timedOut = false;
	  if ( timedOut ) response.sendRedirect(request.getContextPath()+"/Logout");
	  
	  out.print("<h1>Welcome, " + bean.getName() + "</h1>");
	  if(bean.getFavoriteSnack() != null){
		out.print("<p>Your favorite snack is " + bean.getFavoriteSnack() + ".</p>");
	  }
  }
%>

<form action="<%= request.getContextPath() %>/Login" method="get">  
   	Favorite snack:<input type="text" name="snack"/> 
   	<input type="submit" value="send"/>  
</form>
<p>press this button to log out: </p>
<form action="<%= request.getContextPath() %>/Logout" method="post"> 
   	<input type="submit" value="logout"/>  
</form>

</body>
</html>