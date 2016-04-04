<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Komentarz został zgłoszony</title>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />

<div id="pp">
<p>Komentarz został zgłoszony</p>
<a href="${pageContext.request.contextPath}/przepis?id=${id}">Wróć do strony przepisu</a>
</div>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>