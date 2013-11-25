<!-- create user  -->
<div id="createForm" title="Create a New User">
	<span id="createTips">All form fields are required.</span>
	<form>
		<fieldset>
			<label for="firstName">First Name</label>
			<input type="text" name="firstName" id="firstName" class="text ui-widget-content ui-corner-all" maxlength="16" />
			<label for="lastName">Last Name</label>
			<input type="text" name="lastName" id="lastName" value="" class="text ui-widget-content ui-corner-all" maxlength="16" />
			<label for="phoneNumber">Phone Number</label>
			<input type="text" name="phoneNumber" id="phoneNumber" value="" class="text ui-widget-content ui-corner-all" />
			<label for="captcha">Enter Characters <span><a id="refreshCaptcha" href="#">(Refresh)</a></span></label>
			<img src="" id="captcha" />
			<input type="text" name="captchaText" id="captchaText" value="" class="text ui-widget-content ui-corner-all" style="display: inline; vertical-align: top; margin: 10px 0 0 5px" maxlength="${capcthaLength}"/>
		</fieldset>
	</form>
</div>

<!-- update user  -->
<div id="updateForm" title="Update User">
	<span id="updateTips"></span>
	<form>
		<fieldset>
			<label for="firstName">First Name</label>
			<input type="text" name="firstName" id="ufirstName" class="text ui-widget-content ui-corner-all" />
			<label for="lastName">Last Name</label>
			<input type="text" name="lastName" id="ulastName" value="" class="text ui-widget-content ui-corner-all" />
			<label for="phoneNumber">Phone Number</label>
			<input type="text" name="phoneNumber" id="uphoneNumber" value="" class="text ui-widget-content ui-corner-all" />
		</fieldset>
	</form>
</div>