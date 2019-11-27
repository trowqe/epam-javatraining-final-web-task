<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mine" uri="http://koraytugay.com/mytags" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Notes organizing</title>
</head>
<body>


<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.welcome_message"
             var="welcome_message"/>
<fmt:message bundle="${loc}" key="locale.default.locale_botton.en"
             var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.locale_botton.ru"
             var="locale_button_en"/>
<fmt:message bundle="${loc}" key="locale.default.enter_login_message"
             var="enter_login"/>
<fmt:message bundle="${loc}"
             key="locale.default.enter_password_message" var="enter_password"/>

<div class="container">
    <c:if test="${not empty sessionScope.error}">
        <div class="alert alert-dismissible alert-danger my-2 m-2">
            <c:out value="${sessionScope.error}"/>
        </div>
    </c:if>
</div>
<div class="container">
    <div class="container" align="right">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="change_locale"> <input
                type="hidden" name="locale" value="eng"> <input
                type="submit" name="${locale_button_ru}"
                value="${locale_button_ru}"/>
        </form>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="change_locale"> <input
                type="hidden" name="locale" value="ru"> <input
                type="submit" name="${locale_button_en}"
                value="${locale_button_en}"/>
        </form>
    </div>
    <div class="container jumbotron">
        <p class="lead">${welcome_message}${welcome_description}</p>
    </div>

    <div class="continer" align="left">
        <h2>${welcome_message}</h2>
    </div>

    <form action="controller" method="post">
        <div class="form-group">
            <input type="hidden" name="command" value="authorization">

            <label for="exampleInputEmail1">${enter_login}:</label> <input
                type="text" class="form-control" id="exampleInputEmail1"
                placeholder="${enter_login}" name="login" value=""/>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">${enter_password}:</label> <input
                type="password" name="password" class="form-control"
                id="exampleInputPassword1" value=""
                placeholder="${enter_password}"/>
            <small id="emailHelp"
                   class="form-text text-muted">${password_suggestion}</small>
        </div>

        <button type="submit" value="submit" class="btn btn-primary">Submit</button>
    </form>

    <mine:myFirstTag />

    <h3>
        <c:out value="${requestScope.error}"/>
    </h3>

    <a href="controller?command=go_to_registration_page">Registration</a>

</div>
</div>
</body>
</html>