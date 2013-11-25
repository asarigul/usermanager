function closeForm(d) {
	d.dialog("close");
	blurCreateButton();
}

function blurCreateButton() {
	$("#createNewUser").removeClass("ui-state-focus ui-state-hover");
}

function checkLength(element, min, max, fieldName, target) {
	 if (element.val().length > max || element.val().length < min) {
		 
		 var error = fieldName +  " must be " + min;
		 if(min < max) {
			 error += " - " + max;
		 } 
		 fieldError(element, target, error + " characters.");
		 return false;
	 } 
	 return true;
}

function checkRegex(element, regex, errorMessage, target) {
	var val = getElementVal(element);
	if (! regex.test(val)) {
		fieldError(element, target, errorMessage);
		return false;
	}
	return true;
}

function getElementVal(element) {
	var val = element.val();
	if(typeof element.data($.mask.dataName) == "function") {
		val = unmaskNumber(val); 
	}
	return val;
}

function fieldError(element, target, errorMessage) {
	updateErrorMessage(target, errorMessage);
	element.addClass("ui-state-error").focus();
}

function updateErrorMessage(target, message) {
	target.text(message).addClass("ui-state-highlight");
	setTimeout(function() {
		target.removeClass("ui-state-highlight", 1500);
	}, 500);
}

function ajax(loadingText, url, data, successCallback) {
	$("#ajaxLoading>p").text(loadingText).parent().dialog("open");
	
	var jqXHR = $.ajax({
		url: url,
		type: "POST",
		data: data
	});
	
	return jqXHR
		.done(
			function(resp) { 
				successCallback(resp)
			}
		).fail(
			function(jqXHR, status) { 
				$("#ajaxError").dialog("open");
			}
		).always(
			function() {
			$("#ajaxLoading").dialog("close");
		});
}

function formatPhoneNumber(n) {
	while(n.length < 10) {
		n = "0" + n;
	}
	return "(" + n.substring(0, 3) + ") " + n.substring(3, 6) + " " + n.substring(6,8) + " " + n.substring(8);
}

function unmaskNumber(phoneNumber) {
	return phoneNumber.replace(/[^0-9]+/g,"");
}