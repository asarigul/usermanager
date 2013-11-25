<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- existing users  -->
<c:choose>
	<c:when test="${userCount == 0}">
		<h2 id="usersHeader">No users found</h2>
	</c:when><c:otherwise>
		<h2 id="usersHeader">Existing Users</h2>
	</c:otherwise>
</c:choose>

<table id="users" class="ui-widget ui-widget-content">
	<thead>
		<tr class="ui-widget-header">
			<th class="id"></th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Phone Number</th>
			<th><span class="ui-icon ui-icon-pencil"></span></th>						
			<th><span class="ui-icon ui-icon-trash"></span></th>						
		</tr>
	</thead>
	<tbody>
	<c:if test="${userCount > 0}">
		<c:forEach var="user" items="${users}" begin="0" step="1" varStatus="i">
			<tr>
      			<td class="id">${user.id}</td>
      			<td class="firstName">${user.firstName}</td>
	         	<td class="lastName">${user.lastName}</td>
	         	<td class="phoneNumber">${user.phoneNumber}</td>
	         	<td class="update"><button>Update</button></td>
	         	<td class="delete"><button>Delete</button></td>
	        </tr>
 		</c:forEach>
	</c:if>
	</tbody>
</table>