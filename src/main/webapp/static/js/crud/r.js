$(function() {
	CREATE_USER_DEFAULT_TIP = $("#createTips").text();
	
	$("input[type=submit], button").button();
	
	// init create button 
	$("#createNewUser").click(function(event) {
		event.preventDefault();
		blurCreateButton();

		$("#phoneNumber").mask(MASK_PATTERN);
		
		refreshCaptcha();
		
		$("#createForm").dialog("open");
		$("#firstName").val("");
		$("#lastName").val("");
		$("#phoneNumber").val(""); 
		$("#captchaText").val(""); 
		
		$("#captchaText").css("width",  $("#firstName").width() - ($("#captcha").width() || 200) - 10);
	});
	
	// init ajax modals
	$("#ajaxLoading").dialog({
		dialogClass: "no-close", draggable: false, closeOnEscape: false, 
			autoOpen: false,  modal: true
	});
	$("#ajaxError").dialog({
		draggable: false, autoOpen: false, modal: true,
		buttons: { Ok: function() { $(this).dialog( "close" ); }}
	});
	$("#processError").dialog({
		draggable: false, autoOpen: false, modal: true,
		buttons: { Ok: function() { $(this).dialog( "close" ); }}
	});

	var users = $("#users");

	if($("#users > tbody > tr").length > 0) {
		var rows = $("table#users > tbody > tr");
		for(var i = 0; i < rows.length; i++) {
			initUpdateButton($(rows[i]));
			confirmDeletion(rows[i]);
		}
		
		$.each(users.find("td.phoneNumber"), function() {
			var t = $(this);
			t.text(formatPhoneNumber(t.text())); 
		});
		users.show();
	}
});

function initUpdateButton(row) {
	var id = row.children(".id").text();
	var firstName = row.children(".firstName").text();
	var lastName = row.children(".lastName").text();
	var phoneNumber = row.children(".phoneNumber").text();
	
	row.children(".update").children().click(
		function(event) {
			$("#ufirstName").val(firstName);
			$("#ulastName").val(lastName);
			$("#uphoneNumber").val(phoneNumber);
			
			// if unformatted
			if(phoneNumber.length == PHONE_NUMBER_LENGTH) {
				$("#uphoneNumber").val(formatPhoneNumber(phoneNumber));
			}
			
			//
			$("#updateForm")
				.data("userId", id)
				.data("_firstName", firstName)
				.data("_lastName", lastName)
				.data("_phoneNumber", phoneNumber);
			$("#updateForm").dialog("open");
		} 
	);
}

function refreshCaptcha() {
	$("#captcha").attr("src", "captcha/" + new Date().getTime());
}

function processError(title, additionalInfo) {
	$("#processError > span.info").text(additionalInfo)
	$("#processError").dialog("option", "title", title).dialog("open");
}

function confirmDeletion(row) {
	var firstName = $(row).children(".firstName").text();
	var lastName = $(row).children(".lastName").text();
	
	$(row).children(".delete").children().click(
		function(event) {
			$("#confirmDeletion > span.name").text(firstName + " " + lastName);
			$("#confirmDeletion").data("userId", $(row).children(".id").text());
			$("#confirmDeletion").dialog("open");
		} 
	);	
}

