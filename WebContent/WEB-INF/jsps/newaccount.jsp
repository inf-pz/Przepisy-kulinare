<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/withPassword.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js"></script>

<script type="text/javascript">

function onLoad(){

	$("#password").keyup(checkPasswordsMatch);
	$("#confirmpassword").keyup(checkPasswordsMatch);
	
	$("#userForm").submit(canSubmit);

}

function canSubmit(){
	var password = $("#password").val();
	var confirmpassword = $("#confirmpassword").val();
	
	return (password == confirmpassword);

}

function checkPasswordsMatch(){
	var password = $("#password").val();
	var confirmpassword = $("#confirmpassword").val();


	if(password == confirmpassword){
		$("#matchpass").text("");
		$("#matchpass").removeClass("error");		
	}
	
	else{
		$("#matchpass").text("Hasła nie zgadzają się");
		$("#matchpass").addClass("error");
	}
}

$(document).ready(onLoad);

</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Rejestracja</title>

</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />

<br>
<form:form id="userForm" method="post" action="${pageContext.request.contextPath}/nowekonto" commandName="user">
<table>
<tr><td>Login: </td><td><form:input path="login" type="text"/> <br/><div class="error"><form:errors path="login"></form:errors></div> </td> </tr>
<tr><td>Hasło: </td><td><form:input id="password" path="password_h" type="text"/> <br/><div class="error"> <form:errors path="password_h"></form:errors></div> </td> </tr>
<tr><td>Powtórz hasło:</td><td><input id="confirmpassword" name="confirmpassword" type="text"/><div id="matchpass"></div> </td> </tr>
<tr><td>E-mail: </td><td><form:input path="email" type="text"/> <br/><div class="error"><form:errors path="email"></form:errors></div> </td> </tr>
<tr><td></td><td><input value="Zarejestruj" type="submit"/><br/> </td> </tr>
</table>
</form:form>



</body>
</html>