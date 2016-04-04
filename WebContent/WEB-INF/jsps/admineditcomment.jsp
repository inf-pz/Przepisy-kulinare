<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Edytuj komentarz</title>

</head>

<body>
<jsp:include page="/resources/static/navbar.jsp" />

<c:if test="${not empty adminpanel}">
<div id="newcomment">
<p>Autor: <a href="${pageContext.request.contextPath}/przepisy?user=${comment.autor.login}">${comment.autor.login}</a> </p>
<form:form id="commentForm" method="post" action="${pageContext.request.contextPath}/admin/comment/edited?adminpanel=true" commandName="comment">
<table>
<tr><td></td><td><form:textarea value="${comment.text}" path="text" rows="5" cols="70" type="text"/> </td></tr>
<form:input path="id" value="${comment.id}" type="hidden" />


<tr><td></td><td><input class="btn" value="Zapisz" type="submit"/><br/> </td> </tr>
</table>
</form:form>
</div>
</c:if>
<c:if test="${empty adminpanel}">
<div id="newcomment">
<p>Autor: <a href="${pageContext.request.contextPath}/przepisy?user=${comment.autor.login}">${comment.autor.login}</a> </p>
<form:form id="commentForm" method="post" action="${pageContext.request.contextPath}/admin/comment/edited" commandName="comment">


<table>
<tr><td></td><td><form:textarea value="${comment.text}" path="text" rows="5" cols="70" type="text"/> </td></tr>
<form:input path="id" value="${comment.id}" type="hidden" />


<tr><td></td><td><input class="btn" value="Zapisz" type="submit"/><br/> </td> </tr>
</table>
</form:form>
</div>
</c:if>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>