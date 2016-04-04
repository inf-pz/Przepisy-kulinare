<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zgłoś przepis</title>

</head>

<body>
<jsp:include page="/resources/static/navbar.jsp" />

<div id="newcomment">
<p>Podaj powód zgłoszenia</p>
<sf:form id="commentForm" method="post" action="${pageContext.request.contextPath}/admin/przepis/flagged" commandName="report">

<sf:textarea path="text" rows="5" cols="50" type="text"/>
<sf:input path="id" value="${przepis.id}" type="hidden" /> </br> </br>
<input class="btn" value="Zgłoś" type="submit"/>
</sf:form>
</div>

<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>