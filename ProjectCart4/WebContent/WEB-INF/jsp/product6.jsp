<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="homeHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
td.avail{
color:red}
</style>
<script>
	var request;
	function sendInfo() {
		var id = document.product.id.value;
		var pname = document.product.pname.value;
		var price = document.product.price.value;
		var quantity = document.product.quantity.value;
	
		var url = "http://BLRPRGFWD31240:8082/ProjectCart4/checkAvailability?id=" + id+"&pname="+pname+"&price="+price+"&quantity="+quantity;

		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}

		try {
			request.onreadystatechange = getInfo;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}

	function getInfo() {
		if (request.readyState == 4) {
			var val = request.responseText;
			document.getElementById('availability').innerHTML = val;
			/* document.getElementById("myForm").reset(); */
		}
	}
</script>

<title>Insert title here</title>
</head>

<body bgcolor="lemonchiffon">
<center><h2>Product Information</h2></center>
      <p></p>
      <p></p>
<form name="product" action = "http://BLRPRGFWD31240:8082/ProjectCart4/add">
	<table align="center">
		<tr>
			<td></td>
			<td><input type="hidden" name="id" value="6"></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><input type="text" name="pname" value="Shoes"></td>
		</tr>
		<tr>
			<td>Price</td>
			<td><input type="text" name="price" value="500"></td>
		</tr>
		<tr>
			<td>Quantity</td>
			<td><input type="text"  name="quantity" placeholder="Enter quantity" onfocus="this.value=''" required="required"></td><td class="avail"><span id="availability"> </span> </td>
			<td><input type="button" value="CheckAvailability" onClick="sendInfo()">
			</td>
		</tr>
		<tr>
               <td colspan = "2" align="center">
                  <input type = "submit" value = "Add To Cart"/>
               </td>
            </tr>
	</table>
</form>

</body>
</html>