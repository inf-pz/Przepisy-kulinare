<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zgłoszone przepisy</title>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />
<h1>Zgłoszenia:</h1> 


    <table class="zui-table zui-table-horizontal zui-table-highlight">
        <thead>
            <tr>
            	<th>Autor</th>
                <th>Treść</th>
                <th>Przepis</th>
                <th>Data</th>
                <th>Edycja</th>
                <th>Usuń</th>
                <th>Odznacz</th>
            </tr>
            </thead>
            <tbody>

<c:forEach var="report" items="${reports}">
<tr>
			 <td><a href="${pageContext.request.contextPath}/admin/user?login=${report.user.login}">${report.user.login}</a></td>
             <td>${report.text}</td>
             <td><a href="${pageContext.request.contextPath}/przepis?id=${report.przepis.id}">${report.przepis.name}</a></td>
             <td>${report.data}</td>
             <td><a href="${pageContext.request.contextPath}/admin/przepis/edit?id=${report.przepis.id}&adminpanel=true">Edycja</a></td>
             <td><a onclick="return confirm('Czy jesteś pewny że chcesz usunąć ten przepis?')" href="${pageContext.request.contextPath}/admin/przepis/delete?id=${report.przepis.id}&adminpanel=true">Usuń</a></td>
             <td><a href="${pageContext.request.contextPath}/admin/report/delete?id=${report.id}">Usuń zgłoszenie</a></td>
   </tr>
</c:forEach>
</tbody>
</table>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>