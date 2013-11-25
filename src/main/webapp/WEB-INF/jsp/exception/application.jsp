<%@page isErrorPage="true" %><jsp:include page="../header.jsp" >
    <jsp:param name="title" value="Configuration Error" />
</jsp:include>
<body>
	<div id="container">
		<div class="error">
			<h3>Error in application.properties</h3>
			<div>
				<b>Details:</b>
				<p class="message">${pageContext.exception.message}</p>
				<p><i>Fix this and reload the application.</i></p>
			</div>
		</div>
	</div>
</body>
</html>