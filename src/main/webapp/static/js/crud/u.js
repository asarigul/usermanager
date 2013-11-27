$(function() {
	MASK_PATTERN = "(999) 999 99 99";
	
	// init update form
	var firstName = $("#ufirstName");
	var lastName = $("#ulastName");
	var phoneNumber = $("#uphoneNumber"); 
	var all = $([]).add(firstName).add(lastName).add(phoneNumber);
	var tips = $("#updateTips");
	
	// clear error feedback when user types
	all.keyup(function(event) {
		$(this).removeClass("ui-state-error");
		tips.text("");
	});
	
	phoneNumber.mask(MASK_PATTERN);
	
	$("#updateForm").dialog({
		autoOpen: false,
		width: 420,
		modal: true,
		buttons: {
			"Update": function() {
		 		all.removeClass("ui-state-error");
		 		
		 		var isUpdated = (
	 				$(this).data("_firstName") != firstName.val() || 
	 				$(this).data("_lastName") != lastName.val() || 
	 				$(this).data("_phoneNumber") !=  phoneNumber.val()
 				);
		 		
		 		if(isUpdated) {
		 			var valid = checkRegex(firstName, REGEX_FIRSTNAME, FIRSTNAME_VALIDATION_ERROR, tips);
			 		valid = valid && checkRegex(lastName, REGEX_LASTNAME, LASTNAME_VALIDATION_ERROR, tips);
			 		valid = valid && checkRegex(phoneNumber, REGEX_PHONENUMBER, PHONENUMBER_VALIDATION_ERROR, tips);
			 		
			 		if(valid) {
			 			var id = $(this).data("userId"), 
			 				firstNameValue = firstName.val(), 
			 				lastNameValue = lastName.val(), 
			 				phoneNumberValue = unmaskNumber(phoneNumber.val());
			 			
			 			ajax(
		 					"Updating User",
		 					"update",
		 					updateData(id, firstNameValue, lastNameValue, phoneNumberValue),
		 					function(resp) {
		 						if(resp[RESPONSE_RESULT_KEY]) {
		 							closeForm($("#updateForm"));
		 							var td = $("#users > > > td.id").filter(function() {
		 						        return $(this).text() == id; 
		 							});
		 						
		 							var row = td.parent();
		 							
		 							var oldFirstName = $(row).children(".firstName").text();
		 							var oldLastName = $(row).children(".lastName").text();
		 							var oldPhoneNumber = $(row).children(".phoneNumber").text();
		 							
		 							phoneNumberValue = formatPhoneNumber(phoneNumberValue);
		 							$(row).children(".firstName").text(firstNameValue);
		 							$(row).children(".lastName").text(lastNameValue);
		 							$(row).children(".phoneNumber").text(phoneNumberValue);
		 							
		 							row.effect("highlight", {}, 1000, function() {
		 								if(firstNameValue != oldFirstName) {
		 									$(row).children(".firstName").effect("highlight", {}, 500);
		 								}
		 								if(lastNameValue != oldLastName) {
		 									$(row).children(".lastName").effect("highlight", {}, 500);
		 								}
		 								if(phoneNumberValue != oldPhoneNumber) {
		 									$(row).children(".phoneNumber").effect("highlight", {}, 500);
		 								}
		 							});
		 						} else {
		 							processError("Cannot Update User", resp[RESPONSE_INFO_KEY]);
		 						}
		 					}
		 				);
			 		}
		 		} else {
		 			updateErrorMessage(tips, "Nothing to update");
		 		}
		 	},
		 	Cancel: function() {
		 		$("#updateTips").text("");
		 	 	all.val("").removeClass("ui-state-error");
		 	 	closeForm($("#updateForm"))
		 	}
		 },
		 close: function() {
			 $("#updateTips").text("");
		 	 all.val("").removeClass("ui-state-error");
			 blurCreateButton();
		 }
	 });
});


function updateData(id, firstName, lastName, phoneNumber) {
	var data = {};
	data[REQUEST_ID_KEY] = id;
	data[REQUEST_FIRSTNAME_KEY] = firstName; 
	data[REQUEST_LASTNAME_KEY] = lastName; 
	data[REQUEST_PHONENUMBER_KEY] = phoneNumber; 
	return data;
}