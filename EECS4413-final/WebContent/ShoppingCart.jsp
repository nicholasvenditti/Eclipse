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
	<title>Book Store Cart</title>
	<jsp:include page="includes/headlinks.jsp" />
	<style>
		table, th, td {
  			border: 1px solid black;
		}
		table {
  			margin-left: auto;
  			margin-right: auto;
			width: 400px;
		}
	</style>
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<form action='Session' method='POST'>
				<table class='searchTable'>
					<tr>
						<th>Bid</th>
						<th>Title</th>
						<th>Price</th>
						<th>Category</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
					
					<c:choose>
						<c:when test="${empty cartMap}">
							<tr>
								<td colspan='6'>Cart is empty.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="element" items="${cartMap}">
								<tr>
									<td>${element.value.book.bid}</td>
									<td><a href='${pageContext.request.contextPath}/Session/Book?bid=${element.value.book.bid}'>${element.value.book.title}</a></td>
									<td><f:formatNumber type="currency">${element.value.book.price}</f:formatNumber></td>
									<td>${element.value.book.category}</td>
									<td><input type="number" step="1" id="${element.value.book.bid}Qty" name="${element.value.book.bid}Qty" value="${element.value.quantity}" min="0"></input></td>
									<td><f:formatNumber type="currency">${element.value.quantity * element.value.book.price}</f:formatNumber></td>
								</tr>	
							</c:forEach>
						</c:otherwise>
					</c:choose>
						
				</table>
				<button name='updateQty' type='submit' value='true' class='quantityButton'>Update Quantities</button>
			</form>
			<form action='${pageContext.request.contextPath}/Session/Payment' method='POST'>
				<p> Your total cost is <f:formatNumber type="currency">${totalCost}</f:formatNumber> </p> 
				<c:choose>
					<c:when test="${empty cartMap}">
	      				<button name='payment' type='submit' value='true' class='processButton' disabled='true'>Process Payment</button>
	      			</c:when>
	      			<c:otherwise>
	      				<button name='payment' id='payment' type='submit' value='true' class='processButton'>Process Payment</button>
	      			</c:otherwise>
	      		</c:choose>
			</form>
		</div>
	</main>
	<jsp:include page="includes/footer.jsp" />
</body>
</html>
</jsp:root>