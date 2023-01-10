<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
	<c:if test="${logError ne null}">
		<div class='errorMsg'><u>Login Error:</u> ${logError}</div>
	</c:if>
	<c:if test="${regError ne null}">
		<div class='errorMsg'><u>Registration Error:</u> ${regError}</div>
	</c:if>
	<c:if test="${bookError ne null}">
		<div class='errorMsg'><u>BID Error:</u> ${bookError}</div>
	</c:if>
	<c:if test="${purchaseError ne null}">
		<div class='errorMsg'><u>Purchase Error:</u> ${purchaseError}</div>
	</c:if>
	<c:if test="${logSuccess}" >
		<div class='success'><u>Login Success:</u> Welcome, ${uname}!</div>
	</c:if>
	<c:if test="${regSuccess}" >
		<div class='success'><u>Registration Complete:</u> Account successfully created - you may now log-in.</div>
	</c:if>
	<c:if test="${purchaseSuccess ne null}" >
		<div class='success'><u>Order Success:</u> ${purchaseSuccess}</div>
	</c:if>
	<c:if test="${cartUpdate ne null}">
		<div class='success'><u>Cart Updated:</u> ${cartUpdate}</div>
	</c:if>
</jsp:root>