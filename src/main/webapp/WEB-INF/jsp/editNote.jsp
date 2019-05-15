<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="locale.editing_note.title" var="title" />
<fmt:message bundle="${loc}" key="locale.editing_note.description"
	var="description" />
<fmt:message bundle="${loc}" key="locale.editing_note.edit_button"
	var="edit_button" />
<fmt:message bundle="${loc}" key="locale.editing_note.note_title"
	var="note_name" />
<fmt:message bundle="${loc}" key="locale.editing_note.note_text"
	var="note_text" />
<fmt:message bundle="${loc}" key="locale.editing_note.note_preview"
	var="note_preview" />

<title>${title}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<div class="container">
		<form action="controller" method="post" enctype="multipart/form-data">

			<input  type="hidden" name="command" value="edit_note">
			<h4>${description}</h4>
			<h4>${note_name}:</h4>
			<input class="form-control" name="title" value="${sessionScope.note.title}" />
			<h4>${note_text}:</h4>
			<!--  
			<input class="form-control" name="text" value="${sessionScope.note.text}" />
			-->	
			<textarea class="form-control" name="text" id="Textarea" rows="5">${sessionScope.note.text}</textarea>			
			<h4>${note_preview}:</h4>
			<input type="file" name="preview" /> <input type="hidden"
				name="oldPreview" value="${sessionScope.note.preview}" /> <input
				type="hidden" name="idnote" value="${sessionScope.note.idnote}" />
			<input type="hidden" name="iduser"
				value="${sessionScope.note.iduser}" />

			<!-- 
		<h4>${requestScope.note_isPublic}:</h4>
		<input type="radio" name="isPublic" value="true" /> <input
			type="radio" name="isPublic" value="false" checked />
		 -->
			<br />
			<button class="btn btn-primary my-2 m-2" type="submit" name="command" value="edit">${edit_button}</button>
		</form>
	</div>
</body>
</html>