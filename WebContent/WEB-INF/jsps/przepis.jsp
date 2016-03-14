<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<div id="przepis_single">
             <div id="tytul"><p>${przepis.name}</p></div>
             <p>Autor: ${przepis.user.login}</p>
             <p>${przepis.text}</p>

</div>
</div>
<c:forEach var="comment" items="${comments}">
<div id="comment" >
             <p>${comment.autor.login}</p>
             <p>${comment.text}</p>

</div>
</c:forEach>
<div id="newcomment">
<form:form id="commentForm" method="post" action="${pageContext.request.contextPath}/nowycomment" commandName="comment">
<table>
<tr><td></td><td><form:textarea path="text" rows="10" cols="60" type="text"/> <br/><div class="error"><form:errors path="text"></form:errors></div> </td> </tr>
<form:input value="${przepis.id}" path="id" type="hidden"></form:input>
<tr><td></td><td><input value="Dodaj komentarz" type="submit"/><br/> </td> </tr>
</table>
</form:form>
</div>

<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>