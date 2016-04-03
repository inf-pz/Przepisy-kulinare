<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<link href="${pageContext.request.contextPath}/resources/css/withPassword.css" rel="stylesheet" type="text/css" >
<title>Logowanie</title>
</head>
<body onload='document.f.j_username.focus();'>
	<jsp:include page="/resources/static/navbar.jsp" />
	<div id="pp"><h3>Zaloguj się</h3></div>
	
	<c:if test="${param.error != null}">
	<div id="pp">
	<p>Błędny login lub hasło.</p>
	</div>
	
	</c:if>
	<div id="pp">
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
			<tr><td>
				<td><input name="submit" class="btn" type="submit"
					value="Zaloguj się" /><br/> </td> </tr>
			</tr>
		<tr><td></td> </tr><tr><td></td> </tr><tr><td></td> </tr>
	<tr><td></td><td><a class="btn" href="<c:url value="/rejestracja"/> ">Zarejestruj się</a><br/> </td> </tr>
		</div>
	<jsp:include page="/resources/static/footer.jsp" />
			</table>
	</form>
</body>
</html>