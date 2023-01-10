<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="true"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Book Store Purchase</title>
	<jsp:include page="includes/headlinks.jsp" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<div class='paymentContainer'>
				<c:choose>
					<c:when test="${userAccount ne null}">
						<c:choose>
							<c:when test="${not empty addresses}">
								<form action='${pageContext.request.contextPath}/Session/Confirmation' method='POST'>
									<h2>Shipping Address</h2>
									<div class='addressesBox'>
										<c:forEach var="element" items="${addresses}">
											<!-- AID, Street, Province, Country, Zip, Phone, UID -->
											<table>
												<tbody>
													<tr>
														<c:choose>
															<c:when test="${element.aid eq userAccount.defaultAddress}">
																<td rowspan="4" class='addressCheck'><input type='radio' name='shipAddress' value='${element.aid}' checked="checked" /></td>
															</c:when>
															<c:otherwise>
																<td rowspan="4" class='addressCheck'><input type='radio' name='shipAddress' value='${element.aid}' /></td>
															</c:otherwise>
														</c:choose>
														<td class='addressCol2'>${element.lname}</td>
														<td>${element.fname}</td>
													</tr>
													<tr>
														<td>${element.street}</td>
														<td>${element.province}</td>
													</tr>
													<tr>
														<td>${element.zip}</td>
														<td>${element.country}</td>
													</tr>
													<tr>
														<td colspan='2'>${element.phone}</td>
													</tr>
												</tbody>										
											</table>
										</c:forEach>
									</div>
									<h2>Billing Address</h2>
									<label>Use same address as shipping.</label>
									<input type='checkbox' name='same' checked='checked' onchange='showBilling(this)' />
									<div id='shippingBox' class='addressesBox'>
										<c:forEach var="element" items="${addresses}">
											<!-- AID, Street, Province, Country, Zip, Phone, UID -->
											<table>
												<tbody>
													<tr>
														<c:choose>
															<c:when test="${element.aid eq userAccount.defaultAddress}">
																<td rowspan="4" class='addressCheck'><input type='radio' name='billAddress' value='${element.aid}' checked="checked" /></td>
															</c:when>
															<c:otherwise>
																<td rowspan="4" class='addressCheck'><input type='radio' name='billAddress' value='${element.aid}' /></td>
															</c:otherwise>
														</c:choose>
														<td class='addressCol2'>${element.lname}</td>
														<td>${element.fname}</td>
													</tr>
													<tr>
														<td>${element.street}</td>
														<td>${element.province}</td>
													</tr>
													<tr>
														<td>${element.zip}</td>
														<td>${element.country}</td>
													</tr>
													<tr>
														<td colspan='2'>${element.phone}</td>
													</tr>
												</tbody>										
											</table>
										</c:forEach>
									</div>
									<h2>Payment: Credit Card</h2>
									<div class='cardContainer'>
										<table>
											<tbody>
												<tr>
													<td><label>Credit Card Number</label></td>
													<td><input id='ccardNumber' type='text' name='ccardNumber' placeholder='eg. 4111 1111 1111 1111' oninput='validateCC()' /></td>
												</tr>
												<tr>
													<td><label>Name on Credit Card</label></td>
													<td><input id='ccardOwner' type='text' name='ccardName' placeholder='eg. JOHN SMITH' oninput='validateCC()' /></td>
												</tr>
											</tbody>
										</table>
									</div>
									<h2>Purchase</h2>
									<button id='confirmOrder' name='confirmOrder' type='submit' value='true' class='submitOrder' disabled='true'>Confirm Order</button>
								</form>
							</c:when>
							<c:otherwise>
								<!-- Redirect to Address Setup Here: LOGGED IN, BUT NO ADDRESS --> 
								<h2>Address Book Empty</h2>
								<p>You must have an existing entry in your Address Book to continue purchase.</p>
								<a href='${pageContext.request.contextPath}/Session/AddressBook'>Manage Address Book</a>
								<!--
								<a href='${pageContext.request.contextPath}/Session/Address'>Addresses</a>
								<form action='Session' method='POST'>
									<label for="creditCard">Enter Credit Card Number (will not be saved :D):</label><br />
			      					<input id="creditCard" type="number" placeholder="eg. 4111 1111 1111 1111" name="creditCard" /><br />
			      					<button name='confirmOrder' type='submit' value='true'>Confirm Order</button>
								</form>
								-->
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<h2>You must be logged in to purchase.</h2>
						<label>Already a member?</label>
						<form action='${pageContext.request.contextPath}/Session/Payment' method='POST' class='loginForm'>
							<table>
								<tbody>
									<tr>
										<td><label>User Name:</label></td>
										<td><input type='text' name='uname' placeholder='User Name' /></td>
										<td rowspan="2"><button type='submit' name='paymentLogin' value='true'>Sign-in</button></td>
									</tr>
									<tr>
										<td><label>Password:</label></td>
										<td><input type='password' name='passw' placeholder='password' /></td>
									</tr>
								</tbody>
							</table>
						</form>
						<label>New Customer? </label> 
						<a href='${pageContext.request.contextPath}/Session/Register'>Register Now!</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</main>
	<jsp:include page="includes/footer.jsp" />
</body>
</html>
</jsp:root>