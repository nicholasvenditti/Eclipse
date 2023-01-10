<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
<header>
<div class='headstrip'>
	<div class='wrapper'>
		<nav class='linknav'>
			<ul>
				<li><a href='${pageContext.request.contextPath}/Session'>Home</a></li>
				<li><a href='${pageContext.request.contextPath}/Session/Cart'>Cart</a></li>
				<c:if test="${userAccount ne null}">
					<li><a href='${pageContext.request.contextPath}/Session/AddressBook'>Addresses</a></li>
				</c:if>
				<c:if test="${utype eq 1}">
					<li><a href='${pageContext.request.contextPath}/Session/Admin'>Admin</a></li>
				</c:if>
			</ul>
		</nav>
		<nav class='usernav'>
			<c:choose>
				<c:when test="${loggedIn}">
					<ul>
						<li><span><a href='javascript:void(0)'>${uname}</a></span></li>
						<li><a href='${pageContext.request.contextPath}/Session/?signout=1'>Logout</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul>
						<li>
							<form action='${pageContext.request.contextPath}/Session' method='POST'>
								<input id='unameL' type='text' name='uname' placeholder='User Name' />
								<input id='passwL' type='password' name='passw' placeholder='Password' />
								<button id='loginB' type='submit' name='loginB' value='true'>Sign-In</button>
							</form>
						</li>
						<li><a href='${pageContext.request.contextPath}/Session/Register'>Register</a></li>
					</ul>
					<div class='clright'></div>
				</c:otherwise>
			</c:choose>
			</nav>	
		</div>
	</div>
</header>
</jsp:root>