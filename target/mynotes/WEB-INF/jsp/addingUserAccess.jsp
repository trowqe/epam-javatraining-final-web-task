<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${title}</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc"/>
	<fmt:message bundle="${loc}" key="locale.adding_user_access.enter_id"
		var="enter_id" />
	<fmt:message bundle="${loc}" key="locale.adding_user_access.choose_access_level"
		var="choose_access_level" />
	<fmt:message bundle="${loc}" key="locale.adding_user_access.choose_access_level"
		var="choose_access_level" />
		<fmt:message bundle="${loc}" key="locale.adding_user_access.choose_access_level.full_access"
		var="full_access" />
		<fmt:message bundle="${loc}" key="locale.adding_user_access.choose_access_level.comment_only_access"
		var="comment_only_access" />	
		<fmt:message bundle="${loc}" key="locale.adding_user_access.choose_access_level.read_only_access"
		var="read_only_access" />	
		
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />

	<br />
	<div class="container">
		<form action="controller" method="post">
			<fieldset>
				<label>${enter_id}</label> <input type="hidden" name="command"
					value="add_user_access_to_note" /> <input type="hidden"
					name="idnote" class="form-control" value="${requestScope.idnote}" />
				${enter_id}: <input class="form-control" type="text" name="iduser"
					value="" /> <br /> ${enter_access_level} <select name="access"
					class="form-control">
					<option class="form-check-input" value=2>${full_access }</option>
					<option class="form-check-input" value=1>${comment_only_access}</option>
					<option class="form-check-input" value=0>${read_only_access}</option>

				</select> <br /> <input type="submit" name="submit" value="add" />
			</fieldset>
		</form>
	</div>
</body>
</html>