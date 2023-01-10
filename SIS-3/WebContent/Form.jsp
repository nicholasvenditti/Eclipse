<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Form.jsp</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/mc.js">;</script>
		<style>
			table, th, td {
  				border: 1px solid black;
			}
		</style>
	</head>
	<body>
		<main>
			<div style="background-color: whitesmoke;">
				<fieldset>
					<legend>Student Information System</legend>
					<form action="Sis" method="POST" onsubmit="return validate();">
						<label for="namePrefix">Name Prefix:</label><br/>
						<input type="text" id="namePrefix" name="namePrefix"></input><br/>
						<label for="credit_taken">Minimum Credit Taken:</label><br/>
						<input type="number" id="credit_taken" name="credit_taken" value="0"></input><br/>
						<button action="submit" name="report" value="true">Report</button><br/>
						<button action="submit" name="xml" value="true">Generate XML</button><br/>
						<button action="submit" name="json" value="true">Report JSON</button><br/>
						<button name="ajax" value="true" onclick="doSimpleAjax('/SIS-3/Sis/Ajax');return false;">Report JSON (Ajax)</button>
					</form>
				</fieldset>
			</div><br/>
			
			<c:if test="${requestScope['report']}">
				<div>
					
					<c:choose>
						<c:when test="${requestScope['numOfEntries'] ne '1'}">
							There are ${requestScope['numOfEntries']} entries.
						</c:when>
						<c:otherwise>
							There is 1 entry.
						</c:otherwise>
					</c:choose><br/><br/>
					
					<table>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Credits taken</th>
							<th>Credits to graduate</th>
						</tr>
						
						<c:forEach var="element" items="${requestScope['studentMap']}">
							<tr>
								<td>${element.value.sid}</td>
								<td>${element.key}</td>
								<td>${element.value.credit_taken}</td>
								<td>${element.value.credit_graduate}</td>
							</tr>	
						</c:forEach>
						
					</table>
				</div>
			</c:if>
				
			<pre id="ajaxTarget" lang="xml"></pre>
		</main>
	</body>
</html>
</jsp:root>