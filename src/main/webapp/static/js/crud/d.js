$(function() {
	NO_USERS_TEXT = $("#usersHeader").text();
	
	$("#confirmDeletion").dialog({
		autoOpen: false,
		resizable: false,
		modal: true,
		buttons: {
			"Delete": function() {
				closeForm($(this));
				var id = $(this).data("userId");
				ajax(
					"Deleting User",
					"delete",
					deleteData(id),
					function(resp) {
						if(resp[RESPONSE_RESULT_KEY]) {
							var row = $('#users > > > td.id').filter(
									function() { return $(this).text() == id;}
								).parent();
							
							row.effect("highlight", {}, 1000, function() { 
								row.remove();
								if($("#users > tbody > tr").length == 0) {
									$("#usersHeader").text(NO_USERS_TEXT);
									$("#users").hide();
								}
							});
						} else {
							processError("Cannot Delete User", resp[RESPONSE_INFO_KEY]);
						}
					}
				);
			}, 
			Cancel: function() {
				closeForm($(this));
			}
		}
	});
});

function deleteData(id) {
	var data = {};
	data[REQUEST_ID_KEY] = id;
	return data;
}
