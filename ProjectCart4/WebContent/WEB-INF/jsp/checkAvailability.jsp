
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:choose>
<c:when test="${status==1}">
<% out.println("available"); %>
<img alt="available" src="images/yes.jpg" width="25" height="25">
</c:when>
<c:when test="${status==-1}">
<%out.println("enter quantity"); %>
</c:when>
<c:otherwise>
<%out.println("not available"); %>
<img alt="notavailable" src="images/no.png" width="25" height="25">
</c:otherwise>
</c:choose>
</body>
</html>