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
<div id="przepisGlobal">
<c:forEach var="przepis" items="${przepisy}">
<div id="przepis">
             <p>${przepis.name}</p>
             <p>Przepis:${przepis.text}</p>
             <p>Autor:${przepis.user.login}</p>
</div>
</c:forEach>
</div>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>