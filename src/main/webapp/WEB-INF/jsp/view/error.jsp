<jsp:include page="../header.jsp" >
    <jsp:param name="title" value="Request Processing Error" />
</jsp:include>
<body>
	<div id="container">
		<div class="error">
			<h3>Your request cannot be processed at this time</h3>
			<div class="message">
				<p>
					<b>Server says:</b> <i>${info}</i>
				</p>
			</div> 
		</div>
	</div>
</body>
</html>