<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Dodaj przepis</title>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />


<sf:form method="post" accept-charset="utf-8"  commandName="przepis" enctype="multipart/form-data" action="${pageContext.request.contextPath}/docreateprzepis">
<table>
<tr><td>Tytuł: </td><td><sf:input path="name" id="name"/></td> </tr>
<tr><td>Opis: </td><td><sf:textarea path="text" id="text" rows="15" cols="25"/></td> </tr>
<tr><td>Zdjęcie: </td><td><input name="photo" type="file"/> </td></tr>
<tr><td></td><td><input value="Dodaj" class="btn" type="submit"></td> </tr>
</table>
</sf:form>


<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>