<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>${przepis.name}</title>
</head>
<body>

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.5";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<jsp:include page="/resources/static/navbar.jsp" />
<div id="przepisGlobal">
<div id="przepis_single">
             <div id="tytul">
             <div id="tylKolor"><p>${przepis.name}</p></div>
             <div id="czasP"><p><img style="margin-bottom:-8px;"src="http://www.przepisownia.pl/sites/all/themes/frontend/thermomix/images/icons/icon_time.png" alt="Czas przygotowania" height="30" width="30">Czas przygotowania: ${przepis.czas} min.</p></div>
             </div>
             <div class="hr"><hr /></div>
             <div id="przepis-photo-skladniki">
			<div id="przepis-left"><img class="obrazekprz" src="getPhoto/<c:out value="${przepis.id}"/>.do"></div>
			<div id="przepis-right"><div id="tylKolor2"><p>Składniki:</p></div>${przepis.skladniki}</div>
			</div>
			 <div id="inne"><div class="hr"><hr /></div></div>
			 <div id="tylKolor2"><p>Sposób przygotowania:</p> </div>
			  <div id="sposob"><p>${przepis.text}</p></div>
			  <div class="hr"><hr /></div>
			 <p><img style="margin-bottom:-8px;"src="https://cdn4.iconfinder.com/data/icons/user-avatar-flat-icons/512/User_Avatar-46-32.png" alt="Autor" height="32" width="32"> Autor: <a href="${pageContext.request.contextPath}/przepisy?user=${przepis.user.login}">${przepis.user.login}</a><p>
			 <p><img style="margin-bottom:-8px;"src="https://cdn2.iconfinder.com/data/icons/web-and-apps-interface/32/Date-32.png" alt="Data" height="32" width="32"> Dodano: ${przepis.data}</p>
             <div class="hr"><hr /></div>
             <p><img style="margin-bottom:-10px;"src="https://cdn1.iconfinder.com/data/icons/user-ui-vol-1-3/25/user_ui_notify_error_reject_interface-32.png" alt="Zgloszenie" height="32" width="32"> <a href="${pageContext.request.contextPath}/admin/przepis/flag?id=${przepis.id}">zgłoś nadużycie</a></p>
			<div class="fb-share-button" data-href="request.getAttribute("javax.servlet.forward.request_uri")" data-show-faces="true" data-layout="button"></div>       
             </br>
             <sec:authorize access="hasRole('admin')">
             <div class="hr"><hr /></div>
             <p> Panel moderatora: <a class="btn" href="${pageContext.request.contextPath}/admin/przepis/edit?id=${przepis.id}" >Edytuj</a><a class="btn" href="${pageContext.request.contextPath}/admin/przepis/delete?id=${przepis.id}" onclick="return confirm('Czy jesteś pewny że chcesz usunąć ten przepis?')" >Usuń</a> </p>
             </sec:authorize>  
             <div class="hr"><hr /></div>
			<div id="tylKolor"><p>Komentarze:</p></div>
			<c:forEach var="comment" items="${comments}">
			<div id="comment" >
			<div id="comm_text">
             <p>
             <a href="${pageContext.request.contextPath}/przepisy?user=${comment.autor.login}">${comment.autor.login}</a> (${comment.data})
             <a href="${pageContext.request.contextPath}/admin/comment/flag?id=${comment.id}"> <img style="margin-bottom:-2px;" src="https://cdn3.iconfinder.com/data/icons/softwaredemo/PNG/16x16/Flag2_Green.png" /></a>
             <sec:authorize access="hasRole('admin')"> 
             <a href="${pageContext.request.contextPath}/admin/comment/edit?id=${comment.id}"> <img style="margin-bottom:-1px;" src="https://cdn0.iconfinder.com/data/icons/social-messaging-ui-color-shapes/128/write-circle-green-16.png" /></a> 
             <a href="${pageContext.request.contextPath}/admin/comment/delete?id=${comment.id}" onclick="return confirm('Czy jesteś pewny że chcesz usunąć komentarz?')">  <img style="margin-bottom:-1px;" src="https://cdn0.iconfinder.com/data/icons/social-messaging-ui-color-shapes/128/close-circle-red-16.png" /></a> 
             </sec:authorize>
             </p>
             <p></p>
             <p>${comment.text}</p>
</div>
</div>
</c:forEach>
<div id="newcomment">
<form:form id="commentForm" method="post" action="${pageContext.request.contextPath}/nowycomment" commandName="comment">
<table>
<tr><td></td><td><form:textarea placeholder="Twoja opinia :-)" path="text" rows="5" cols="60" type="text"/> <br/><div class="error"><form:errors path="text"></form:errors></div> </td> </tr>
<form:input value="${przepis.id}" path="id" type="hidden"></form:input>
<tr><td></td><td><input class="btn" value="Dodaj komentarz" type="submit"/><br/> </td> </tr>
</table>
</form:form>
</div>
</div>
</div>
<jsp:include page="/resources/static/footer.jsp" />
</body>
</html>
