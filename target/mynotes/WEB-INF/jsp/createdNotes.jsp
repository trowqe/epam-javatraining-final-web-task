<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.created_notes.title"
	var="title" />
<fmt:message bundle="${loc}" key="locale.created_notes.notes_list"
	var="notes_list_message" />
<fmt:message bundle="${loc}" key="locale.main.menu_button.share_note"
	var="share_note" />
<fmt:message bundle="${loc}" key="locale.main.menu_button.edit_note"
	var="edit_note" />
<fmt:message bundle="${loc}" key="locale.main.menu_button.delete_note"
	var="delete_note" />
<fmt:message bundle="${loc}" key="locale.main.menu_button.show_comments"
	var="show_comments" />
<title>${title}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<div class="container">
		<c:choose>
			<c:when
				test="${sessionScope.quanity != null && sessionScope.quanity > 0 }">
				<h5>${notes_list_message}:</h5>
				<form action="controller" method="post">
					<button class="btn btn-primary" type="submit" name="command"
						value="show_users_with_access">${share_note}</button>
					<button class="btn btn-primary" type="submit" name="command"
						value="go_to_updating_note">${edit_note}</button>
					<button class="btn btn-primary" type="submit" name="command"
						value="delete_note">${delete_note}</button>
					<button class="btn btn-primary" type="submit" name="command"
						value="go_to_comments">${show_comments}</button>
					<table class="table table-hover my-2 m-2">
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
									<td><input type="radio" name="idnote"
										value="${note.idnote}" /></td>
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
			</c:when>
			<c:otherwise>
				<div class="alert alert-dismissible alert-info">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${no_notes}
				</div>
			</c:otherwise>
		</c:choose>
		
		<jsp:include page="/WEB-INF/jsp/common/pageSwitcher.jsp" />

	</div>
	<!--  c:if's for page switching -->
</body>
</html>