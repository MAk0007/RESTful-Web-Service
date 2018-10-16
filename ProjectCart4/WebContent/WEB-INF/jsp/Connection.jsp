<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>  
<%  
String name=request.getParameter("uname");
String pass=request.getParameter("pwd");

if(name==null || name.trim().equals("")){  
out.println("Please enter username");
}else if(pass==null || pass.trim().equals("")){
	out.print("Please enter password");

}else{  
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://10.6.184.246:3306/springdemo","root1","root1");  
PreparedStatement ps=con.prepareStatement("select * from users where username=?");  
ps.setString(1,name);
String uname=null,pw=null;
ResultSet rs=ps.executeQuery();  
while(rs.next()){  
uname=rs.getString(1);
out.println("in try");
pw=rs.getString(2);
}  
if(name.equals(uname)){
	if(pass.equals(pw)){
		out.println("inside if");
		HttpSession session1 = request.getSession(true);
		session1.setAttribute("name", uname);
		RequestDispatcher rd = request.getRequestDispatcher("/homepage");
		rd.forward(request, response);
	}else{
	out.println("Incorrect password..!!");
}
	}else{
	out.println("No user found in database..!!");
}
con.close();  
}catch(Exception e){e.printStackTrace();}  
}  
%>


</body>
</html>