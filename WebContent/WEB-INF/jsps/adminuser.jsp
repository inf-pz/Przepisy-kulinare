<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Użytkownik ${user.login}</title>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />
<a href="${pageContext.request.contextPath}/admin/users">wróc do listy użytkowników</a>
<h1>${user.login}              
<a class="btn" onclick="return confirm('Czy jesteś pewny że chcesz usunąć użytkownika i wszystkie jego przepisy?')" href="${pageContext.request.contextPath}/admin/user/delete?user=${user.login}">Usuń</a>
</h1> 
<h2>Dane użytkownika:</h2>
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


<tr>
             <td>${user.login}</td>
             <td>${user.email}</td>
             <td>${user.data_rejestracji}</td>
             <td>${user.last_active}</td>

   </tr>
</tbody>
</table>

<h2>Przepisy:</h2>
    <table class="zui-table zui-table-horizontal zui-table-highlight">
        <thead>
            <tr>
                <th>Tytul</th>
                <th>Dodano</th>
                <th>Usuń</th>
            </tr>
            </thead>
            <tbody>

<c:forEach var="przepis" items="${przepisy}">
<tr>
             <td><a href="${pageContext.request.contextPath}/przepis?id=${przepis.id}">${przepis.name}</a></td>
             <td>${przepis.data}</td>
             <td><a onclick="return confirm('Czy jesteś pewny że chcesz usunąć ten przepis?')" href="${pageContext.request.contextPath}/admin/przepis/delete?id=${przepis.id}">Usuń</a></td>
   </tr>
</c:forEach>
</tbody>
</table>

<h2>Komentarze:</h2>
    <table class="zui-table zui-table-horizontal zui-table-highlight">
        <thead>
            <tr>
                <th>Treść</th>
                <th>Dodano</th>
                <th>Usuń</th>
            </tr>
            </thead>
            <tbody>

<c:forEach var="comment" items="${comments}">
<tr>
             <td><a href="${pageContext.request.contextPath}/przepis?id=${comment.przepis.id}">${comment.text}</a></td>
             <td>${comment.data}</td>
             <td><a onclick="return confirm('Czy jesteś pewny że chcesz usunąć ten komentarz?')" href="${pageContext.request.contextPath}/admin/comment/delete?id=${comment.id}">Usuń</a></td>
   </tr>
</c:forEach>
</tbody>
</table>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>