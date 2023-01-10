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
	<title>Book Store Home</title>
	<jsp:include page="includes/headlinks.jsp" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<h1 class='homeTitle'>EECS4480 Bookstore</h1>
			<div class="search-container">
	    		<form action='Session' method='POST'>
	      			<input id="search" type="text" placeholder="Search..." name="search" /><br />
					<input type="radio" id="name" name="searchBy" value="name" checked="checked" />
	  				<label for="name">Search By Name</label><br />
	  				<input type="radio" id="category" name="searchBy" value="category" />
	  				<label for="category">Search By Category</label><br /><br />
	      			<button name='searchB' type='submit' value='true'>Search</button>
				</form>
			</div>
			<br />
			<br />
			<text class='disclaimer'>**THIS IS A MOCK-UP WEBSITE THAT IS BEING RECORDED FOR EDUCATIONAL PURPOSES ONLY. DO NOT ENTER REAL INFORMATION!**</text>
		</div>
	</main>
	<jsp:include page="includes/footer.jsp" />
</body>
</html>
</jsp:root>