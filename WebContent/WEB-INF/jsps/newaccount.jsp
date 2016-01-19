<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Rejestracja</title>

</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />


<form method="post" action="${pageContext.request.contextPath}/nowekonto">
<table>
<tr><td>Login: </td><td><input name="login" type="text"></td> </tr>
<tr><td>Hasło </td><td><input name="password_h" type="text"></td> </tr>
<tr><td>Powtórz hasło</td><td><input name="confirmPassword" type="text"></td> </tr>
<tr><td>E-mail </td><td><input name="email" type="text"></td> </tr>
<tr><td></td><td><input value="Zarejestruj" type="submit"></td> </tr>
</table>
</form>



</body>
</html>