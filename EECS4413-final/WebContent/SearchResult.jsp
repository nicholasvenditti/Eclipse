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
	<title>Book Store Home</title>
	<jsp:include page="includes/headlinks.jsp" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<table class="searchTable">
				<tr>
					<th>Bid</th>
					<th>Title</th>
					<th>Category</th>
					<th>Price</th>
				</tr>
				
				<c:forEach var="element" items="${searchResults}">
					<tr>
						<td>${element.bid}</td>
						<td><a href='${pageContext.request.contextPath}/Session/Book?bid=${element.bid}'>${element.title}</a></td>
						<td>${element.category}</td>
						<td><f:formatNumber type="currency">${element.price}</f:formatNumber></td>
					</tr>	
				</c:forEach>
			</table>
		</div>
	</main>
	<jsp:include page="includes/footer.jsp" />
</body>
</html>
</jsp:root>