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
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/res/style.css?v=1.5" type="text/css" title="cse4413" media="screen, print" />
</head>
<body onload="ajaxVisit('${pageContext.request.contextPath}/Session/Ajax')">
	<jsp:include page="includes/header.jsp" />
	<jsp:include page="includes/alerts.jsp" />
	<main>
		<div class='wrapper'>
			<c:choose>
				<c:when test="${bookError ne null}">
					<div class='bookDisplay'>
						<h2> </h2>
						<h2>Book was not found.</h2>
					</div>
				</c:when>
				<c:otherwise>
					<input id='bidRecord' type='hidden' value='${book.bid}' />
					<div class='bookDisplay'>
						<h1>${book.title}</h1>
						<h2>(${book.bid})</h2>
						<div class='buyRow'>
							<table>
								<tbody>
									<tr>
										<td><span>Price: </span><f:formatNumber type="currency">${book.price}</f:formatNumber></td>
										<td>
											<form action='${pageContext.request.contextPath}/Session/Book' method='POST'>
												<input type='hidden' value='${book.bid}' name='bid' />
												<button type='submit' name='addCart' value='true'>Add to cart</button>
											</form>
										</td>
										<td>
											<c:choose>
												<c:when test="${cartMap.containsKey(book.bid)}">
													${cartMap[book.bid].quantity}
												</c:when>
												<c:otherwise>
													0
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
			        <div class='reviews'>
			        	<table>
			        		<tbody>
			        			<tr>
			        				<th>Reviewer</th>
			        				<th>Rating</th>
			        				<th>Review</th>
			        			</tr>
					        	<c:forEach var="element" items="${reviewList}">
					        		<tr>
					        			<td>${element.author}</td>
					        			<td>${element.rating}</td>
					        			<td>${element.review}</td>
					        		</tr>
					        	</c:forEach>
				        	</tbody>
			        	</table>
			        </div>
			        <div class='reviewContainer'>
			        <c:choose>
				        <c:when test="${userAccount ne null}">
				        	<form action='${pageContext.request.contextPath}/Session/Book' method='POST'>
				        		<input type='hidden' name='bid' value='${book.bid}' />
				        		<input type='hidden' name='author' value='${userAccount.uname}' />
				        		<table>
				        			<tbody>
					        			<tr>
					        				<td><label>Reviewer</label></td>
					        				<td>
					        					<div class='ratingContainer'>
											        <div class="rate">
											            <input type="radio" id="star5" name="rate" value="5" />
														<label for="star5" title="text">5 stars</label>
														<input type="radio" id="star4" name="rate" value="4" />
														<label for="star4" title="text">4 stars</label>
														<input type="radio" id="star3" name="rate" value="3" />
														<label for="star3" title="text">3 stars</label>
														<input type="radio" id="star2" name="rate" value="2" />
														<label for="star2" title="text">2 stars</label>
														<input type="radio" id="star1" name="rate" value="1" />
														<label for="star1" title="text">1 star</label>
											        </div>
										        </div>
					        				</td>
					        				<td rowspan='2'>
					        					<button id='reviewSubmit' name='reviewSubmit' type='submit' value='true'>Submit</button>
					        				</td>
				        				</tr>
				        				<tr>
				        					<td><input type='text' value='${userAccount.uname}' disabled='true' /></td>
				        					<td>
				        						<textarea id='reviewText' rows="4" cols="50" name="comment" placeholder="Add review here...">.</textarea>
				        					</td>
				        				</tr>
				        			</tbody>
				        		</table>
					        </form>
				        </c:when>
				        <c:otherwise>
				        	You must be logged in to post a review.
				        </c:otherwise>
			        </c:choose>
			        </div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
	<jsp:include page="includes/footer.jsp" />
</body>
</html>
</jsp:root>