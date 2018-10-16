<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="homeHeader.jsp"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>

body {font-family: Arial, Helvetica, sans-serif;}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    -webkit-animation-name: fadeIn; /* Fade in the background */
    -webkit-animation-duration: 0.4s;
    animation-name: fadeIn;
    animation-duration: 0.4s
}

/* Modal Content */
.modal-content {
    position: fixed;
    bottom: 0;
    background-color: #fefefe;
    width: 100%;
    -webkit-animation-name: slideIn;
    -webkit-animation-duration: 0.4s;
    animation-name: slideIn;
    animation-duration: 0.4s
}

/* The Close Button */
.close {
    color: white;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

.modal-header {
    padding: 2px 16px;
    background-color: #5cb85c;
    color: white;
}

.modal-body {padding: 2px 16px;}

.modal-footer {
    padding: 2px 16px;
    background-color: #5cb85c;
    color: white;
}

/* Add Animation */
@-webkit-keyframes slideIn {
    from {bottom: -300px; opacity: 0} 
    to {bottom: 0; opacity: 1}
}

@keyframes slideIn {
    from {bottom: -300px; opacity: 0}
    to {bottom: 0; opacity: 1}
}

@-webkit-keyframes fadeIn {
    from {opacity: 0} 
    to {opacity: 1}
}

@keyframes fadeIn {
    from {opacity: 0} 
    to {opacity: 1}
}
</style>
<title>Cart</title>
</head>
<body bgcolor="lemonchiffon">
     <div align="center">
            <h1>Your CART</h1>
         
            <table border="1">
                <tr>
                <th>Name</th>
                <th>Product Price</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Select</th>
               </tr>
                 <c:set var="total" value="${0}"/>
                <c:forEach var="product" items="${listProduct}">
                <tr>
                    
                    <td>${product.pname}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.price*product.quantity}</td>
                    <td>
                        <a href="<c:url value="/edit/${product.id}" />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/delete/${product.id}"/>">Delete</a>
                    </td>
                     <c:set var="total" value="${total + product.price*product.quantity}" />
                </tr>
                </c:forEach>   
                <tr>
<td colspan="2"><center>Total price</center></td>
<td colspan="3" align="center" >${total}</td>
</tr>       
            </table>
        </div>
        <p></p>
<p></p>
<center><button id="myBtn">Confirm Payment</button></center>
<div id="myModal" class="modal">
<div class="modal-content">
<div class="modal-header">
<span class="close">&times;</span>
<h2>Confirm Payment</h2>
</div>
<div class="modal-body">
<h5>you have to pay an amount of ${total} INR</h5>
</div>
<div class="modal-footer">
<h4>Thank you for Shopping with SmallMart</h4>
</div>
</div>
</div>
<!-- <form action="http://localhost:8082/ProjectCart1/confirmPayment">
<input type="submit" value="ConfirmPayment">
</form> -->

<script>
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
    window.location.href="http://BLRPRGFWD31240:8082/ProjectCart4/";
}

// When the user clicks anywhere outside of the modal, close it
/* window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
} */
</script>
</body>
</html>