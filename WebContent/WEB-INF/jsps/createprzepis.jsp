<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>



<form method="post" action="${pageContext.request.contextPath}/docreateprzepis">
<table>
<tr><td>Tytuł: </td><td><input name="name" type="text"></td> </tr>
<tr><td>Opis: </td><td><textarea name="text" rows="15" cols="25"></textarea></td> </tr>
<tr><td>ID użytkownika: </td><td><input name="member_id" type="text"></td> </tr>
<tr><td></td><td><input value="Dodaj" type="submit"></td> </tr>
</table>
</form>



</body>
</html>