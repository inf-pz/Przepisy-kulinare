<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Dodaj przepis</title>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />


<form method="post" action="${pageContext.request.contextPath}/docreateprzepis">
<table>
<tr><td>Tytuł: </td><td><input name="name" type="text"></td> </tr>
<tr><td>Opis: </td><td><textarea name="text" rows="15" cols="25"></textarea></td> </tr>
<tr><td></td><td><input value="Dodaj" class="btn" type="submit"></td> </tr>
</table>
</form>


<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>