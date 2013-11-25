$(function() {
	EXISTING_USERS_TEXT = "Existing Users";
	
	var firstName = $("#firstName");
	var lastName = $("#lastName");
	var phoneNumber = $("#phoneNumber"); 
	var captchaText = $("#captchaText"); 
	var all = $([]).add(firstName).add(lastName).add(phoneNumber).add(captchaText);
	
	// clear error feedback when user types
	all.keyup(function(event) {
		$(this).removeClass("ui-state-error");
	});
	
	$("#refreshCaptcha").click(function(event){
		event.preventDefault();
		refreshCaptcha();
	});
	
	$("#createForm").dialog({
		autoOpen: false,
		width: 450,
		modal: true,
		buttons: {
			"Create": function() {
				all.removeClass("ui-state-error");
		 		var tips = $("#createTips");
		 		var valid = checkRegex(firstName, REGEX_FIRSTNAME, FIRSTNAME_VALIDATION_ERROR, tips);
		 		valid = valid && checkRegex(lastName, REGEX_LASTNAME, LASTNAME_VALIDATION_ERROR, tips);
		 		valid = valid && checkRegex(phoneNumber, REGEX_PHONENUMBER, PHONENUMBER_VALIDATION_ERROR, tips);
		 		valid = valid && checkLength(captchaText, CAPTCHA_LENGTH, CAPTCHA_LENGTH, "Captcha", tips);
		 		
		 		if(valid) {
		 			var firstNameValue = firstName.val(), 
		 				lastNameValue = lastName.val(), 
		 				phoneNumberValue = unmaskNumber(phoneNumber.val()), 
		 				captchaValue = captchaText.val();
	
					ajax(
						"Creating User",
						"create",
						createData(firstNameValue, lastNameValue, phoneNumberValue, captchaValue),
						function(resp) {
							if(resp[RESPONSE_RESULT_KEY]) {
								closeForm($("#createForm"));
								
								var row = $("<tr>" + 
									"<td class='id'>" + resp[RESPONSE_INFO_KEY] + "</td>" + 
									"<td class='firstName'>" + firstNameValue + "</td>" + 
						         	"<td class='lastName'>" + lastNameValue + "</td>" + 
						         	"<td class='phoneNumber'>" + formatPhoneNumber(phoneNumberValue) + "</td>" + 
						         	"<td class='update'><button>Update</button></td>" + 
						         	"<td class='delete'><button>Delete</button></td>" + 
					         	"</tr>");
								
								row.find("button").button();
								confirmDeletion(row);
								initUpdateButton(row);
								
								$("#users").prepend(row);
								if($("#users > tbody > tr").length == 1) {
									$("#usersHeader").text(EXISTING_USERS_TEXT);
									$("#users").show();
								}
								row.effect("highlight", {}, 1000);
							} else {
								processError("Cannot Create User", resp[RESPONSE_INFO_KEY]);
							}
						}
					);
	 			}
		 	},
		 	Cancel: function() {
		 		$("#createTips").text(CREATE_USER_DEFAULT_TIP);
		 	 	all.val("").removeClass("ui-state-error");
		 	 	closeForm($("#createForm"))
		 	}
		 },
		 close: function() {
			 $("#createTips").text(CREATE_USER_DEFAULT_TIP);
			 all.val("").removeClass("ui-state-error");
			 blurCreateButton();
		 }
	 });
});

function closeCreateForm() {
	$("#createTips").text(CREATE_USER_DEFAULT_TIP);
 	all.val("").removeClass("ui-state-error");
 	closeForm($("#createForm"))
}

function createData(firstName, lastName, phoneNumber, captcha) {
	var data = {};
	data[REQUEST_FIRSTNAME_KEY] = firstName; 
	data[REQUEST_LASTNAME_KEY] = lastName; 
	data[REQUEST_PHONENUMBER_KEY] = phoneNumber; 
	data[REQUEST_CAPTCHA_KEY] = captcha;
	return data;
}