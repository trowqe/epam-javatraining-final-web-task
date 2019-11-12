<%@ page language="java"
	import="com.epam.finaltask.mynotes.entity.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>welcome page!</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc" />
	<fmt:message bundle="${loc}" key="locale.main.welcome_message"
		var="hello_message" />
	<fmt:message bundle="${loc}" key="locale.default.locale_botton.en"
		var="locale_button_ru" />
	<fmt:message bundle="${loc}" key="locale.default.locale_botton.ru"
		var="locale_button_en" />
	<fmt:message bundle="${loc}" key="locale.main.last_notes"
		var="last_notes" />
	<fmt:message bundle="${loc}" key="locale.main.menu" var="menu" />
	<fmt:message bundle="${loc}"
		key="locale.main.menu_button.get_all_created_notes"
		var="menu_created_notes" />
	<fmt:message bundle="${loc}"
		key="locale.main.menu_button.get_all_available_notes"
		var="menu_available_notes" />
	<fmt:message bundle="${loc}"
		key="locale.main.menu_button.get_notes_you_sharing"
		var="menu_shared_notes" />
	<fmt:message bundle="${loc}" key="locale.main.menu_button.settings"
		var="menu_settings" />
	<fmt:message bundle="${loc}" key="locale.main.menu_button.logout"
		var="menu_logout" />
	<fmt:message bundle="${loc}"
		key="locale.main.menu_button.create_new_note" var="create_new_note" />
	<fmt:message bundle="${loc}" key="locale.main.menu_button.edit_note"
		var="edit_note" />
	<fmt:message bundle="${loc}" key="locale.main.menu_button.delete_note"
		var="delete_note" />
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />


	<div class="container">



		<c:if test="${not empty sessionScope.error}">
			<div class="alert alert-dismissible alert-danger my-2 m-2">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<c:out value="${sessionScope.error}" />
			</div>
		</c:if>

		<c:if test="${not empty sessionScope.success}">
			<div class="alert alert-dismissible alert-success my-2 m-2">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<c:out value="${sessionScope.success}" />
			</div>
		</c:if>

		<c:if test="${not empty sessionScope.info}">
			<div class="alert alert-dismissible alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<c:out value="${sessionScope.info}" />
			</div>

		</c:if>
		<h4 class="my-2 m-2">${menu}</h4>
		<form action="controller" method="post">
			<button class="btn btn-outline-primary my-1 m-1" name="command"
				type="submit" value="go_to_created_notes">${menu_created_notes}</button>
		</form>
		<form action="controller" method="post">
			<button class="btn btn-outline-primary my-1 m-1" name="command"
				type="submit" value="go_to_available_notes">${menu_available_notes}</button>
		</form>

		<form action="controller" method="post">
			<button class="btn btn-outline-primary my-1 m-1" name="command"
				type="submit" value="go_to_shared_notes">${menu_shared_notes}</button>
		</form>
		<!--  <form action="controller" method="post">
			<button class="btn btn-outline-primary my-1 m-1" name="command"
				type="submit" value="shared_notes">${menu_settings}</button>
		</form>
		!-->
		<br /> <br /> <br />

		<h3>${last_notes}</h3>
		<form action="controller" method="post">
			<button class="btn btn-primary" type="submit" name="command"
				value="go_to_creating_note">${create_new_note}</button>
			<button class="btn btn-primary" type="submit" name="command"
				value="go_to_updating_note">${edit_note}</button>
			<button class="btn btn-primary" type="submit" name="command"
				value="delete_note">${delete_note}</button>
			<br />
			<table class="table table-hover my-1 m-1">
				<thead>
					<tr>
						<td>#</td>
						<td>title</td>
						<td>text</td>
						<td>created</td>
						<td>preview</td>
					</tr>
				</thead>

				<c:forEach items="${sessionScope.notes}" var="note">
					<tbody>
						<tr>
							<td><input type="radio" name="idnote" value="${note.idnote}" /></td>

							<td>${note.title}</td>
							<td>${note.text}</td>
							<td>${note.created}</td>
							<td><img
								style="height: 300px; width: 300px; object-fit: contain"
								src="data:image/jpeg;base64,${note.previewEnc}" /></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</form>
	</div>
	<br>
</body>
</html>