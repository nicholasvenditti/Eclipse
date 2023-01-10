<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>UI.jsp</title>
		<link rel="StyleSheet" href="${pageContext.request.contextPath}/res/mc.css" type="text/css" title="cse4413" media="screen, print"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/mc.js">;</script>		
	</head>
	<body>
	
		<c:choose>
			<c:when test="${sessionScope['principal'] eq null}">
				<c:set var="principal" value="10000"/>
			</c:when>
			<c:otherwise>
				<c:set var="principal" value="${sessionScope['principal']}"/>
			</c:otherwise>
		</c:choose>
					
		<c:choose>
			<c:when test="${sessionScope['interest'] eq null}">
				<c:set var="interest" value="10"/>
			</c:when>
			<c:otherwise>
				<c:set var="interest" value="${sessionScope['interest']}"/>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${sessionScope['period'] eq null}">
				<c:set var="period" value="120"/>
			</c:when>
			<c:otherwise>
				<c:set var="period" value="${sessionScope['period']}"/>
			</c:otherwise>
		</c:choose>
		
		<header>
			<h3>OSAP 2020 Calculator</h3>
		</header>
		<nav>
			<a href="http://localhost:8080/OsapCalc-v4/Osap"><b>Home</b></a>&#160;
			<a href="http://localhost:8080/OsapCalc-v4/admin"><b>About</b></a><br/>
		</nav><br/>
		<main>
			<fieldset>
				<legend>${initParam['applicationName']}</legend>
				<form action="Osap" method="POST" class="osapForm" onsubmit="return validate();">
				
					<c:if test="${not empty requestScope['error'] and requestScope['error'] ne 0}">
						<p class="error">
							<c:choose>
								<c:when test="${requestScope['error'] eq 1}">Principal must be greater than 0!</c:when>
								<c:when test="${requestScope['error'] eq 2}">Interest must be between 0 and 100!</c:when>
								<c:when test="${requestScope['error'] eq 3}">Period must be greater than 0!</c:when>
							</c:choose>
						</p>
					</c:if>
				
					<label for="principal">Principal<span id="principal-error" style="display: none;">*</span><br/><small>(total loan amount after studies) </small></label>
					<input type="number" step="0.01" id="principal" name="principal" value="${principal}"></input><br/>
					<label for="interest">Annual Interest Rate<span id="interest-error" style="display: none;">*</span></label>
					<input type="number" step="0.01" id="interest" name="interest" value="${interest}"></input><br/>
					<label for="period">Payment Period<span id="period-error" style="display: none;">*</span><br/><small>(total number of months) </small></label>
					<input type="number" step="0.01" id="period" name="period" value="${period}"></input><br/>
					<label for="grace">Grace Period<br/><small>(Take advantage of 6 month grace period and include grace period interest with your loan balance) </small></label>
					
					<c:choose>
						<c:when test="${sessionScope['grace'] eq 'off'}">
							<input type="checkbox" id="grace" name="grace"></input><br/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="grace" name="grace" checked="checked"></input><br/>
						</c:otherwise>
					</c:choose>
					
					<button action="submit" name="calculate" value="true">Submit</button>
					<button name="ajax" value="true" onclick="doSimpleAjax('/OsapCalc-v4/Osap/Ajax');return false;">Submit (Ajax)</button>
				</form>
				<div id="ajaxTarget"></div>
			</fieldset><br/>
		</main><br/>
		<footer>
			<img src="${pageContext.request.contextPath}/res/Lassonde.png" alt="lassonde" width="100" height="100"/><br/>
			<i>York University</i>
		</footer>
	</body>
</html>
</jsp:root>