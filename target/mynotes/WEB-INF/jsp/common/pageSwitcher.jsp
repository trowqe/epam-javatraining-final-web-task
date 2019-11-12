<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<c:if test="${sessionScope.firstButton != null}">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="go_to_created_notes" />
				<c:choose>
					<c:when test="${sessionScope.currentPage == 1 }">
						<button class="btn btn-primary m-1" type="submit"
							name="pageNumber" value="${sessionScope.firstButton}">${sessionScope.firstButton}</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-outline-primary m-1" type="submit"
							name="pageNumber" value="${sessionScope.firstButton}">${sessionScope.firstButton}</button>
					</c:otherwise>
				</c:choose>

				<c:if test="${sessionScope.butttonPrev != null}">
					<button class="btn btn-outline-primary m-1" type="submit"
						name="pageNumber" value="${sessionScope.buttonPrev}">${sessionScope.buttonPrev}</button>
				</c:if>

				<c:if test="${sessionScope.buttonNow != null}">
					<button class="btn btn-primary m-1" type="submit" name="pageNumber"
						value="${sessionScope.buttonNow}">${sessionScope.buttonNow}</button>
				</c:if>

				<c:if test="${sessionScope.buttonNext != null}">
					<button class="btn btn-outline-primary m-1" type="submit"
						name="pageNumber" value="${sessionScope.buttonNext}">${sessionScope.buttonNext}</button>
				</c:if>

				<c:choose>
					<c:when
						test="${sessionScope.lastButton != null && sessionScope.currentPage == sessionScope.lastButton}">
						<button class="btn btn-primary m-1" type="submit"
							name="pageNumber" value="${sessionScope.lastButton}">${sessionScope.lastButton}</button>
					</c:when>
					<c:otherwise>
						<c:if test="${sessionScope.lastButton != null}">
							<button class="btn btn-outline-primary m-1" type="submit"
								name="pageNumber" value="${sessionScope.lastButton}">${sessionScope.lastButton}</button>
						</c:if>
					</c:otherwise>
				</c:choose>
			</form>
		</c:if>
		
		<!-- 
<c:if test="${sessionScope.firstButton != null}">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="go_to_available_notes" />
				<c:choose>
					<c:when test="${sessionScope.currentPage == 1 }">
						<button class="btn btn-primary m-1" type="submit"
							name="pageNumber" value="11${sessionScope.firstButton}">${sessionScope.firstButton}</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-outline-primary m-1" type="submit"
							name="pageNumber" value="22${sessionScope.firstButton}">${sessionScope.firstButton}</button>
					</c:otherwise>
				</c:choose>

				<c:if test="${sessionScope.butttonPrev != null}">
					<button class="btn btn-outline-primary m-1" type="submit"
						name="pageNumber" value="33${sessionScope.buttonPrev}">${sessionScope.buttonPrev}</button>
				</c:if>

				<c:if test="${sessionScope.buttonNow != null}">
					<button class="btn btn-primary m-1" type="submit" name="pageNumber"
						value="${sessionScope.buttonNow}">44${sessionScope.buttonNow}</button>
				</c:if>

				<c:if test="${sessionScope.buttonNext != null}">
					<button class="btn btn-outline-primary m-1" type="submit"
						name="pageNumber" value="${sessionScope.buttonNext}">55${sessionScope.buttonNext}</button>
				</c:if>

				<c:choose>
					<c:when
						test="${sessionScope.lastButton != null && sessionScope.currentPage == sessionScope.lastButton}">
						<button class="btn btn-primary m-1" type="submit"
							name="pageNumber" value="${sessionScope.lastButton}">${sessionScope.lastButton}</button>
					</c:when>
					<c:otherwise>
					<c:if test="${sessionScope.lastButton != null }]">
						<button class="btn btn-outline-primary m-1" type="submit"
							name="pageNumber" value="${sessionScope.lastButton}">${sessionScope.lastButton}</button>
					</c:if>
					</c:otherwise>
				</c:choose>
			</form>
		</c:if>
		 -->
</body>
</html>