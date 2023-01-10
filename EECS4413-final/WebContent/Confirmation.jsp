<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:f="http://java.sun.com/jsp/jstl/fmt" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="true"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Book Store Confirmation</title>
	<jsp:include page="includes/headlinks.jsp" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<div class='confirmationContainer'>
				<h2>Cart Items</h2>
				<table class='searchTable'>
					<tr>
						<th>Bid</th>
						<th>Title</th>
						<th>Price</th>
						<th>Category</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
					<c:forEach var="element" items="${cartMap}">
						<tr>
							<td>${element.value.book.bid}</td>
							<td>${element.value.book.title}</td>
							<td><f:formatNumber type="currency">${element.value.book.price}</f:formatNumber></td>
							<td>${element.value.book.category}</td>
							<td><input type="text" value="${element.value.quantity}" min="0" readonly="readonly" /></td>
							<td><f:formatNumber type="currency">${element.value.quantity * element.value.book.price}</f:formatNumber></td>
						</tr>	
					</c:forEach>
					<tr>
						<td colspan="3"><h2>Total Cost</h2></td>
						<td colspan="3"><f:formatNumber type="currency">${totalCost}</f:formatNumber></td>
					</tr>
				</table>
				<h2>Shipping Address</h2>
				<div class='confirmAddress'>
					<table>
						<tbody>
							<tr>
								<td>${shippingAddress.lname}</td>
								<td>${shippingAddress.fname}</td>
								<td>${shippingAddress.phone}</td>
							</tr>
							<tr>
								<td colspan='2'>${shippingAddress.street}</td>
								<td>${shippingAddress.province}</td>
							</tr>
							<tr>
								<td colspan='2'>${shippingAddress.zip}</td>
								<td>${shippingAddress.country}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<h2>Billing Address</h2>
				<div class='confirmAddress'>
				<table>
					<tbody>
						<tr>
							<td>${billingAddress.lname}</td>
							<td>${billingAddress.fname}</td>
							<td>${billingAddress.phone}</td>
						</tr>
						<tr>
							<td colspan='2'>${billingAddress.street}</td>
							<td>${billingAddress.province}</td>
						</tr>
						<tr>
							<td colspan='2'>${billingAddress.zip}</td>
							<td>${billingAddress.country}</td>
						</tr>
					</tbody>
				</table>
				</div>
				<h2>Payment: Credit Card</h2>
				<div class='cardContainer'>
					<table>
						<tbody>
							<tr>
								<td><label>Credit Card Number</label></td>
								<td><input type='text' value='${ccNumber}' disabled='true' /></td>
							</tr>
							<tr>
								<td><label>Name on Credit Card</label></td>
								<td><input type='text' value='${ccName}' disabled='true' /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<h2>Confirm Purchase</h2>
				<form action='${pageContext.request.contextPath}/Session' method='POST'>
					<input type='hidden' name='shipAdd' value='${shippingAddress.aid}' />
					<button id='confirmPurchase' name='confirmPurchase' type='submit' value='true' class='submitOrder'>Order</button>
				</form>
			</div>
		</div>
	</main>
	<jsp:include page="includes/footer.jsp" />
</body>
</html>
</jsp:root>