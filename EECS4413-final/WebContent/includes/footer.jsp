<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"  version="2.0">
	<footer>
		<div class='wrapper'>
			<nav>
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
		</div>
	</footer>
	<script type="text/javascript">
    	function trq(){(trq.q=trq.q||[]).push(arguments);}
    	trq('account', 't-617378f80ea6365a3e056f4f');
    	var _paq=_paq||[];
    	_paq.push(['trackPageView']);
    	_paq.push(['enableLinkTracking']);

    	(function() {
        	var u="//capturly.com/";
        	_paq.push(["setTrackerUrl", u+"capturly-track.php"]);
        	_paq.push(['setSiteId', '5285']);
        	var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        	g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'capturly-track-js.js';
        	s.parentNode.insertBefore(g,s);
    	})();
	</script>                     
</jsp:root>