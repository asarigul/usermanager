<jsp:include page="../header.jsp" >
    <jsp:param name="title" value="Welcome to Simple User Manager" />
</jsp:include>
<body>
	<script type="text/javascript">
		CAPTCHA_LENGTH = ${capcthaLength},
		
		PHONE_NUMBER_LENGTH = ${PHONE_NUMBER_LENGTH},
		REGEX_FIRSTNAME = /${REGEX_FIRSTNAME}/,
		REGEX_LASTNAME = /${REGEX_LASTNAME}/,
		REGEX_PHONENUMBER = /${REGEX_PHONE_NUMBER}/,
		
		RESPONSE_RESULT_KEY = "${RESPONSE_RESULT_KEY}";
		RESPONSE_INFO_KEY = "${RESPONSE_INFO_KEY}",

		REQUEST_ID_KEY = "${REQUEST_ID_KEY}",
		REQUEST_FIRSTNAME_KEY = "${REQUEST_FIRSTNAME_KEY}",
		REQUEST_LASTNAME_KEY = "${REQUEST_LASTNAME_KEY}",
		REQUEST_PHONENUMBER_KEY = "${REQUEST_PHONENUMBER_KEY}",
		REQUEST_CAPTCHA_KEY = "${REQUEST_CAPTCHA_KEY}",
		
		FIRSTNAME_VALIDATION_ERROR = "${FIRSTNAME_VALIDATION_ERROR}",
		LASTNAME_VALIDATION_ERROR = "${LASTNAME_VALIDATION_ERROR}",
		PHONENUMBER_VALIDATION_ERROR = "${PHONENUMBER_VALIDATION_ERROR}";
	</script>

	<div id="container">
		<button id="createNewUser">Create a New User</button>

<%@ include file="users.jsp" %>
<%@ include file="forms.jsp" %>
<%@ include file="modals.jsp" %>

	</div>
</body>
</html>