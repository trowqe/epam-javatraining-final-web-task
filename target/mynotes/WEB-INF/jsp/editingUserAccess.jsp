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
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_email_messsage" var="enter_email" />
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />

	<br />
	<div class="container">
		<form action="controller" method="post">
			<fieldset>
				<label for="exampleInputEmail1">${enter_id}:</label> <input
					type="hidden" name="command" value="edit_user_access_to_note" /> <input
					type="hidden" name="idnote" class="form-control"
					value="${sessionScope.idnote}" /> ${enter_id}: 
					<input type="hidden" name="iduser" value="${sessionScope.idTargetUser}" /> 
					<br />
					 ${enter_access_level} <select
					name="access"  class="form-control">
					<option class="form-check-input" value=2 selected="selected">${full_access }</option>
					<option class="form-check-input" value=1>${comment_only_access }</option>
					<option class="form-check-input" value=0>${read_only_access }</option>

				</select> <br /> <input type="submit" name="submit" value="add" />
			</fieldset>
		</form>
	</div>
</body>
</html>