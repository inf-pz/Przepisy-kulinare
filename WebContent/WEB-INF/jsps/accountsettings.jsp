<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><link href="${pageContext.request.contextPath}/resources/css/withPassword.css" rel="stylesheet" type="text/css" >
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ustawienia konta</title>

</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />

<br>
<div id="pp">
<form id="userForm" method="post" action="${pageContext.request.contextPath}/ustawieniaUpdate">
<table>
<tr><td>Obecne hasło: </td><td><input id="password_old" name="password_old" type="password"/> <br/><div class="error"><c:out value="${error}"/> </div> </td> </tr>
<tr><td>Nowe hasło: </td><td><input id="password" name="password" type="password"/> <br/><div class="error"> </div> </td> </tr>
<tr><td>Powtórz hasło:</td><td><input id="confirmpassword" name="confirmpassword" type="password"/><div id="matchpass"></div> </td> </tr>
<tr><td>E-mail: </td><td><input name="email" type="text" value="<c:out value="${email}"/>" /> <br/><div class="error"></div> </td> </tr>
<tr><td></td><td><input value="Zapisz" class="btn" type="submit"/><br/> </td> </tr>
</table>
</form>
</div>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>