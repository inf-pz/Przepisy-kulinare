<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/navbar.css" rel="stylesheet" type="text/css" >
</head>
<body>
<nav>
<ul>
<li><a href="${pageContext.request.contextPath}/">Strona główna</a></li>
<li><a href="${pageContext.request.contextPath}/przepisy">Lista przepisów</a></li>
<li><a href="${pageContext.request.contextPath}/nowyprzepis">Dodaj przepis</a></li>
<sec:authorize access="isAuthenticated()">
<div class="dropdown">
  <span>Konto</span>
  <div class="dropdown-content">
<a href="${pageContext.request.contextPath}/ustawienia">Ustawienia</a>
<a href="${pageContext.request.contextPath}/mojeprzepisy">Moje przepisy</a>
  </div>
</div>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
<li><a href="${pageContext.request.contextPath}/login">Zaloguj</a></li>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<li><a href="<c:url value='/j_spring_security_logout'/> "> Wyloguj</a></li>
</sec:authorize>
</ul>
</nav>
<br>
</body>
</html>