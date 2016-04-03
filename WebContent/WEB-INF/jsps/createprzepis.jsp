<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Dodaj przepis</title>
<script language="JavaScript">

function Validate() {

	var image =document.getElementById("photo").value;

	if(image!=''){

		var checkimg = image.toLowerCase();

		if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)){
			alert("Dozwolone formaty to .jpg, .png, .jpeg");
			document.getElementById("photo").focus();
			return false;
			}
	}
	return true;

}

</script>
</head>

<body>
<jsp:include page="/resources/static/navbar.jsp" />

<div id="pp">
<sf:form id="form" method="post" accept-charset="utf-8"  commandName="przepis" enctype="multipart/form-data" onSubmit="return Validate()" action="${pageContext.request.contextPath}/docreateprzepis">
<table>
<tr><td>Tytuł: </td><td><sf:input path="name" id="name"/></td> </tr>
<tr><td>Składniki: </td><td><sf:textarea path="skladniki" id="skladniki" placeholder="składniki oddzielaj enterami" rows="15" cols="50"/></td> </tr>
<tr><td>Opis: </td><td><sf:textarea path="text" id="text" rows="15" cols="50"/></td> </tr>
<tr><td>Czas przygotowania: </td><td><sf:input path="czas" placeholder="w minutach" value="" id="czas"/> minut</td> </tr>
<tr><td>Zdjęcie: </td><td><input name="photo" id="photo" type="file"/> </td></tr>
<tr><td><div class="error"></div></td></tr>
<tr><td></td><td><input value="Dodaj" class="btn" type="submit"></td> </tr>
</table>
</sf:form>
</div>

<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>