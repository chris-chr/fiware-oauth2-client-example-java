<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 

//filter to catch the logged in users and 
//redirects them to the user page 
if(request.getSession(false).getAttribute("access_token") == null){ %>
<a href="login">Login</a>
<% }else{ response.sendRedirect("user");} %>
</body>
</html>