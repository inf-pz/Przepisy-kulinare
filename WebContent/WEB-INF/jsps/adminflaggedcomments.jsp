<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zgłoszone komentarze</title>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />
<h1>Zgłoszone komentarze:</h1> 


    <table class="zui-table zui-table-horizontal zui-table-highlight">
        <thead>
            <tr>
            	<th>Autor</th>
                <th>Treść</th>
                <th>Przepis</th>
                <th>Dodano</th>
                <th>Edycja</th>
                <th>Usuń</th>
                <th>Odznacz</th>
            </tr>
            </thead>
            <tbody>

<c:forEach var="comment" items="${comments}">
<tr>
			 <td><a href="${pageContext.request.contextPath}/admin/user?login=${comment.autor.login}">${comment.autor.login}</a></td>
             <td>${comment.text}</td>
             <td><a href="${pageContext.request.contextPath}/przepis?id=${comment.przepis.id}">${comment.przepis.name}</a></td>
             <td>${comment.data}</td>
             <td><a href="${pageContext.request.contextPath}/admin/comment/edit?id=${comment.id}&adminpanel=true">Edycja</a></td>
             <td><a onclick="return confirm('Czy jesteś pewny że chcesz usunąć ten komentarz?')" href="${pageContext.request.contextPath}/admin/comment/delete?id=${comment.id}&adminpanel=false">Usuń</a></td>
             <td><a href="${pageContext.request.contextPath}/admin/comment/unflag?id=${comment.id}">Usuń zgłoszenie</a></td>
   </tr>
</c:forEach>
</tbody>
</table>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>