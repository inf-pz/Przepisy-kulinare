<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Lista przepisow</title>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />
<c:if test="${not empty user}">
<div id="pp">
    <p>Przepisy użytkownia <c:out value="${user}"></c:out>:<p>
    </div>
    </c:if>
<c:if test="${not empty msg}">
<div id="pp">
    <p>${msg}<p>
    </div>
</c:if>
<div id="przepisGlobal">
<c:forEach var="przepis" items="${przepisy}">
<div id="przepis" onclick="location.href='${pageContext.request.contextPath}/przepis?id=${przepis.id}';" style="cursor: pointer;">
             <div id="tylKolor3"><p>${przepis.name}</p></div>
			<div id="obraz_maly"><img class="obrazekmaly" src="getPhoto/<c:out value="${przepis.id}"/>.do"></div>
 			<p style="">Autor: ${przepis.user.login}</p>
            
</div>
</c:forEach>
</div>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>