<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"  version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="true"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Book Store Address Book</title>
	<jsp:include page="includes/headlinks.jsp" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<div class='addressBookContainer'>
				<c:choose>
					<c:when test="${userAccount ne null}">
						<form action='${pageContext.request.contextPath}/Session/Address' method='POST' class='newAddress'>
							<button type='submit' name='goAddAddress'>Add a New Address</button>
						</form>
						<c:if test="${not empty addresses}">
							<form action='${pageContext.request.contextPath}/Session/AddressBook' method='POST' class='defaultAddressBook'>
								<div class='addressesBox'>
									<c:forEach var="element" items="${addresses}">
										<table>
											<tbody>
												<tr>
													<c:choose>
														<c:when test="${element.aid eq userAccount.defaultAddress}">
															<td rowspan="4" class='addressCheck'><input type='radio' name='newDefault' value='${element.aid}' checked="checked" /></td>
														</c:when>
														<c:otherwise>
															<td rowspan="4" class='addressCheck'><input type='radio' name='newDefault' value='${element.aid}' /></td>
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
								<button type='submit' name='defaultAddButton' value='true' >Set as Default Address</button>
							</form>
						</c:if>
					</c:when>
					<c:otherwise>
						<h2>You must be logged in to edit your address book.</h2>
						<label>Already a member?</label>
						<form action='${pageContext.request.contextPath}/Session/AddressBook' method='POST' class='loginForm'>
							<table>
								<tbody>
									<tr>
										<td><label>User Name:</label></td>
										<td><input type='text' name='uname' placeholder='User Name' /></td>
										<td rowspan="2"><button type='submit' name='addressLogin' value='true'>Sign-in</button></td>
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