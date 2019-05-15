<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="locale.show_users_with_access.title"
	var="title" />
<fmt:message bundle="${loc}"
	key="locale.show_users_with_access.users_list_message"
	var="users_list_message" />
<fmt:message bundle="${loc}"
	key="locale.show_users_with_access.add_user_to_note"
	var="add_user_to_note" />
<fmt:message bundle="${loc}"
	key="locale.show_users_with_access.edit_users_accessing_note"
	var="edit_users_accessing_note" />
<fmt:message bundle="${loc}"
	key="locale.show_users_with_access.remove_users_from_note"
	var="remove_users_from_note" />

<title>${title}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<div class="container">
		<h5>${users_list_message}</h5>

		<form action="controller" method="post">

			<button class="btn btn-primary" type="submit" name="command"
				value="go_to_adding_user_access_to_note">${add_user_to_note}</button>
			<button class="btn btn-primary" type="submit" name="command" value="go_to_editing_users_access_to_note">${edit_users_accessing_note}</button>
			<button class="btn btn-primary" type="submit" name="command"
				value="remove_user_from_access_to_note">${remove_users_from_note}</button>
			<input type="hidden" name="idnote" value="${sessionScope.idnote}">
			<table class="table table-hover my-2 m-2">
				<thead>
					<tr>
						<td>#</td>
						<td>user_login</td>
						<td>access_level</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.users}" var="user">
						<tr>
							<td><input type="radio" name="iduser" value="${user.id}" /></td>

							<td>${user.login}</td>
							<td>${user.noteAccess}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>