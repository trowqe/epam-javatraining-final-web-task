<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc"/>
	<fmt:message bundle="${loc}" key="locale.creating_note.title"
	var="title" />
<fmt:message bundle="${loc}" key="locale.creating_note.description"
	var="description" />
<fmt:message bundle="${loc}" key="locale.creating_note.create_button"
	var="create_button" />
<fmt:message bundle="${loc}" key="locale.creating_note.note_title"
	var="note_name" />
<fmt:message bundle="${loc}" key="locale.creating_note.note_text"
	var="note_text" />
<fmt:message bundle="${loc}" key="locale.creating_note.note_preview"
	var="note_preview" />

<title>${title}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<div class="container">
		<form action="controller" method="post" enctype="multipart/form-data">
			<input type="hidden" name="command" value="create_note">
			<h4>${description}</h4>
			<br />
			<h4>${note_name}:</h4>
			<input class="form-control" name="title" />
			<h4>${note_text}:</h4>
			<textarea class="form-control" name="text" id="Textarea" rows="5"></textarea>
			<h4>${note_preview}:</h4>
			<div class="input-group mb-3">
				<div class="custom-file">
					<input type="file" class="custom-file-input" id="inputGroupFile02"
						name="preview"> <label class="custom-file-label"
						for="inputGroupFile02"></label>
				</div>
				<div class="input-group-append">
					<button class="input-group-text" type="submit" name="command"
						value="create">${create_button}</button>
					<!-- 	<span class="input-group-text" id="d">Upload</span> -->
				</div>
			</div>
			<input type="hidden" name="iduser" value="${sessionScope.iduser}" />

			<!-- 
		<h4>${note_isPublic}:</h4>
		<input type="radio" name="isPublic" value="true" /> <input
			type="radio" name="isPublic" value="false" checked />
		 -->
			<br />
			<button class="btn btn-primary my-2 m-2" type="submit" name="command"
				value="create">${create_button}</button>
		</form>
	</div>
</body>
</html>