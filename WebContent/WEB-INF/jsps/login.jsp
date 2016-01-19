<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>


<title>Logowanie</title>
</head>
<body onload='document.f.j_username.focus();'>
	<jsp:include page="/resources/static/navbar.jsp" />
	<h3>Zaloguj się</h3>
	
	<c:if test="${param.error != null}">
	
	<p>Błędny login lub hasło.</p>
	
	
	</c:if>
	
	<form name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
		<table>
			<tr>
				<td>Login:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Hasło:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Zaloguj się" /></td>
			</tr>
		</table>
	</form>
	
	<p><a href="<c:url value="/rejestracja"/> ">Zarejestruj się</a></p>
</body>
</html>