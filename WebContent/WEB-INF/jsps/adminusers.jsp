<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Lista użytkowników</title>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />
<h1>Użytkownicy:</h1>
    <table class="zui-table zui-table-horizontal zui-table-highlight">
        <thead>
            <tr>
                <th>Login</th>
                <th>Email</th>
                <th>Data rejestracji</th>
                <th>Ostanie logowanie</th>
            </tr>
            </thead>
            <tbody>


<c:forEach var="user" items="${users}">
<tr>
             <td><a href="${pageContext.request.contextPath}/admin/user?login=${user.login}">${user.login}</a></td>
             <td>${user.email}</td>
             <td>${user.data_rejestracji}</td>
             <td>${user.last_active}</td>

   </tr>
</c:forEach>
</tbody>
</table>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>