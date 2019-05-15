<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/litera/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<body>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.locale" var="loc" />
	<fmt:message bundle="${loc}" key="locale.footer.logout" var="logout" />
	<fmt:message bundle="${loc}" key="locale.footer.go_to_main"
		var="profile" />
	<fmt:message bundle="${loc}" key="locale.footer.registration"
		var="registration" />
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="controller?command=go_to_index">tr-organiser</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="true" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="navbar-collapse collapse" id="navbarColor01" style="">
			<ul class="navbar-nav mr-auto">
			</ul>
			<c:if test="${sessionScope.iduser != null }">
				<!--//logout  & about button   -->
				<p class="navbar-right my-2 p-2 text-white">You are logged as ${sessionScope.user.login} 	</p>
				<form class="form-inline my-2 my-lg-0" action="controller"
					method="post">
					<button class="btn btn-secondary my-2 m-2" type="submit"
						name="command" value="logout">${logout}</button>
					<!--  <input type="hidden" name="command" value="logout" /> <input
						type="submit" value="${logout}" /> 
				</form>
				<form action="controller" method="post"> -->
					<button class="btn btn-secondary my-2 m-2" type="submit"
						name="command" value="go_to_main_page">${profile}</button>
					<!--
					<input type="hidden" name="command" value="go_to_main_page" /> <input
						type="submit" value="${profile}" /> -->
				</form>
			</c:if>
			<c:if test="${sessionScope.iduser == null }">
				<!--  //register & log in button -->
				<form class="form-inline my-2 my-lg-0" action="controller"
					method="post">
					<button class="btn btn-secondary my-2 my-sm-0" type="submit"
						name="command" value="go_to_registration_page">${registration}</button>
				</form>
			</c:if>
		</div>
	</nav>
</body>
</html>