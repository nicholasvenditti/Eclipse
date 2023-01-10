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
	<title>Book Store Admin</title>
	<jsp:include page="includes/headlinks.jsp" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<div class='adminContainer'>
				<c:choose>
					<c:when test="${userAccount ne null and userAccount.utype eq 1}">
						<h1>Admin Logs</h1>
						<ol>
							<li><a href='${pageContext.request.contextPath}/res/monthly.json'>Monthly Sales</a></li>
							<li><a href='${pageContext.request.contextPath}/res/topsales.json'></a>Top Sales</li>
						</ol>
					</c:when>
					<c:otherwise>
						<h2>You are not logged into an admin account.</h2>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</main>
	<jsp:include page="includes/footer.jsp" />
</body>
</html>
</jsp:root>