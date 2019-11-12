<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>
</head>
<body>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc"/>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_email_messsage" var="enter_email" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_login_message" var="enter_login" />
	<fmt:message bundle="${loc}"
		key="locale.registration.enter_password_message" var="enter_password" />
	zzz
	<div align="right">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="change_locale"> <input
				type="hidden" name="locale" value="ru"> <input type="submit"
				name="submit" value="ru" />
		</form>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="change_locale"> <input
				type="hidden" name="locale" value="eng"> <input
				type="submit" name="submit" value="eng" />
		</form>
	</div>

	<div class="container">
		<form action="controller" method="post">
			<fieldset>
				<input type="hidden" name="command" value="registration">
				<div class="form-group">
					<label for="exampleInputEmail1">${enter_email}:</label> <input
						name="email" type="email" class="form-control"
						id="exampleInputEmail1" aria-describedby="emailHelp"
						placeholder="${enter_email}"> <small id="emailHelp"
						class="form-text text-muted">${email_suggestion}</small>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">${enter_login}:</label> <input
						type="text" class="form-control" id="exampleInputEmail1"
						placeholder="${enter_login}" name="login" value="" />
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">${enter_password}:</label> <input
						type="password" name="password" class="form-control"
						id="exampleInputPassword1" value="" /> <small id="emailHelp"
						class="form-text text-muted">${password_suggestion}</small>
				</div>
				<button class="btn btn-primary" type="submit" value="register">Submit</button>

				<!--   <input type="submit" name="submit" value="register" /> -->
			</fieldset>
		</form>
	</div>
</body>
</html>