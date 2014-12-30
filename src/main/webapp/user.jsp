<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% 
//filter to catch the non logged in users 
//and redirects them to the index page
if(request.getSession(false).getAttribute("access_token") == null){
	response.sendRedirect("index.jsp");
}	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

//gets the json data which was seted at the user servlet
//parses it and then prints it
String data = (String)request.getAttribute("data");
JSONObject obj = new JSONObject(data.toString());
%>
<form action="logout">
<h1>Hello : <%= obj.getString("displayName") %></h1>
<span>Nickname : <%= obj.getString("nickName") %> </span><br>
<span>E-mail : <%= obj.getString("email") %></span><br>
<span>ID : <%= obj.getInt("id") %></span><br>
<span>Actor ID : <%= obj.getInt("actorId") %></span><br>
<button type="submit">Log out</button>
</form>
</body>
</html>