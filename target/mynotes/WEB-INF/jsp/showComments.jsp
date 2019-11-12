<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.comments.title" var="title" />
<fmt:message bundle="${loc}" key="locale.comments.comments_list"
	var="comments_list_message" />
<fmt:message bundle="${loc}" key="locale.comments.no_comments" var="no_comments" />
<fmt:message bundle="${loc}" key="locale.comments.add_comment"
	var="post_comment" />
<fmt:message bundle="${loc}" key="locale.comments.delete_comment"
	var="delete_comment" />

<title>${title}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<div class="container">
		<c:choose>
			<c:when
				test="${sessionScope.quanity != null && sessionScope.quanity > 0 }">
				<h5>${quanity_of_notes_message}:${sessionScope.quanity}"</h5>
				<h5>${comments_list_message}:</h5>
				<form action="controller" method="post">
					<button class="btn btn-primary m-1" type="submit" name="command"
						value="add_comment">${add_comment}</button>
					<button class="btn btn-primary m-1" type="submit" name="command"
						value="delete_comment">${delete_сomment}</button>
					<div class="form-group m-2">
						<input type="text" class="form-control" id="enterComment"
							name="text" placeholder="${enter_comment}">
					</div>
					<table class="table table-hover my-2 m-2">
						<thead>
							<tr>
								<td>#</td>
								<td>user</td>
								<td>comment</td>
								<td>created</td>
							</tr>
						</thead>

						<c:forEach items="${sessionScope.comments}" var="comment">
							<tbody>
								<tr>
									<td><input type="radio" name="idComment"
										value="${comment.idComment}" /></td>
									<td>${comment.author}</td>
									<td>${comment.text}</td>
									<td>${comment.date}</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<div class="alert alert-dismissible alert-info m-2">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${no_comments}
				</div>
			</c:otherwise>
		</c:choose>

		<jsp:include page="/WEB-INF/jsp/common/pageSwitcher.jsp" />


	</div>
</body>
</html>