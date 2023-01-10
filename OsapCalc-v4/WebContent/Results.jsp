<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:f="http://java.sun.com/jsp/jstl/fmt" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Results.jsp</title>
		<link rel="StyleSheet" href="${pageContext.request.contextPath}/res/mc.css" type="text/css" title="cse4413" media="screen, print"/>
	</head>
	<body>
		<header>
			<h3>OSAP 2020 Calculator</h3>
		</header>
		<nav>
			<a href="http://localhost:8080/OsapCalc-v4/Osap"><b>Home</b></a>&#160;
			<a href="http://localhost:8080/OsapCalc-v4/admin"><b>About</b></a><br/>
		</nav><br/>
		<main class="resultForm">
			<fieldset>
				<legend>${initParam['applicationName']}</legend>
				<form action="Osap" method="POST">
 					<strong>Grace Period Interest: </strong><f:formatNumber type="currency">${sessionScope['graceInterest']}</f:formatNumber><br/><br/>
 					<strong>Monthly payments: </strong><f:formatNumber type="currency">${sessionScope['monthlyPayment']}</f:formatNumber><br/><br/>
 						Calculations are based on a fixed rate on Prime Rate + 5%<br/><br/>
 					<button action="restart" name="restart" value="true">Re-compute</button><br/><br/>
				</form>
			</fieldset>
		</main><br/><br/>
		<footer>
			<img src="${pageContext.request.contextPath}/res/Lassonde.png" alt="lassonde" width="100" height="100"/><br/>
			<i>York University</i>
		</footer>
	</body>
</html>
</jsp:root>