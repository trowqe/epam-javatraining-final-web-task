<%@ page language="java" import="com.epam.finaltask.mynotes.entity.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>you are banned!</title>
</head>
<body>
 	<fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.banned.description" var="locale_banned_message" />
 
 <h1>Your account is terminated.</h1>
 <h2>

 	<c:out value="${requestScope.user.login}" />, ${locale_banned_message}
 </h2>
 
</body>
</html>