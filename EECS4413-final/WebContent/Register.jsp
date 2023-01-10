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
	<title>Book Store Registration</title>
	<jsp:include page="includes/headlinks.jsp" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<h1>Register</h1>
			<c:choose>
				<c:when test="${not loggedIn}">
					<form action='${pageContext.request.contextPath}/Session' method='POST' class='regForm'>
						<label for="uname">User Name:</label>
						<input id="uname" type="text" name="uname" placeholder="[a-Z0-9_-] (max. length 20)" />
						<label for="pass1">Password:</label>
						<input id="pass1" type="password" name="pass1" placeholder="Password (min. length 8)" />
						<input id="pass2" type="text" name="pass2" placeholder="Password (Confirm)" />
						<button type="submit" name="regSubmit" value="true">Sign-Up</button>
					</form>
				</c:when>
				<c:otherwise>
					<div class='regForm'>
						You already have an account!
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
	<jsp:include page="includes/footer.jsp" />
</body>
</html>
</jsp:root>