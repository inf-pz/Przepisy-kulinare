<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/withPassword.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Rejestracja</title>

</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />


<form:form method="post" action="${pageContext.request.contextPath}/nowekonto" commandName="user">
<table>
<tr><td>Login: </td><td><form:input path="login" type="text"/> <br/><div class="error"><form:errors path="login"></form:errors></div> </td> </tr>
<tr><td>Hasło: </td><td><form:input path="password_h" type="text"/> <br/><div class="error"> <form:errors path="password_h"></form:errors></div> </td> </tr>
<tr><td>Powtórz hasło:</td><td><input name="confirmPassword" type="text"/> </td> </tr>
<tr><td>E-mail: </td><td><form:input path="email" type="text"/> <br/><div class="error"><form:errors path="email"></form:errors></div> </td> </tr>
<tr><td></td><td><input value="Zarejestruj" type="submit"/><br/> </td> </tr>
</table>
</form:form>



</body>
</html>