<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Przepis dodany!</title>
<style>
body {
	background-color: #f5f6f8;
	width: 1000px;
    margin-left: auto;
    margin-right: auto;
    font-family: 'Lucida Grande', 'Helvetica Neue', Helvetica, Arial, sans-serif;
     padding: 20px 50px 150px;
    
}

/* Giving a background-color to the nav container. */
nav {
   margin: 0px 0;
   background-color: #d0dcc1;
   border-radius:2em;
}
 
/* Since we'll have the "ul li" "float:left"
 * we need to add a clear after the container. */
 
nav:after {
   content:"";
   display:table;
   clear:both;
}
 
/* Removing padding, margin and "list-style" from the "ul",
 * and adding "position:reltive" */
nav ul {
   padding:0;
   margin:0;
   list-style: none;
   position: relative;
   }
 
/* Positioning the navigation items inline */
nav ul li {
   margin: 0px;
   display:inline-block;
   float: left;
   }
 
/* Styling the links */
nav a {
   display:block;
   padding:0 10px;
   color:#000000;
   font-size:20px;
   line-height: 60px;
   text-decoration:none;
   border-radius:2em;
}
 
/* Background color change on Hover */
nav a:hover {
   background-color: #c2dcc1;
}
input[type="text"] {
  padding: 10px;
  border: solid 1px #c9c9c9;
  transition: border 0.3s;
  margin-top: 5px;
}
input[type="radio"] {
  padding: 10px;
  margin-left: 40px;
}

.zui-table {
    border: solid 1px #9ec090 ;
    border-collapse: collapse;
    border-spacing: 0;
    font: normal 14px Arial, sans-serif;
    width: 100%;
}
.zui-table thead th {
    background-color: #9ec090;
    border: solid 1px #9ec090;
    color: #fff;
    padding: 10px;
    text-align: left;
}
.zui-table tbody td {
    border: solid 1px #9ec090;
    color: #333;
    padding: 10px;
}
.zui-table-highlight tbody tr:hover {
    background-color: #a6c090;
}
.zui-table-horizontal tbody td {
    border-left: none;
    border-right: none;
}
</style>
</head>
<body>
<jsp:include page="/resources/static/navbar.jsp" />
Przepis został dodany!
</br>
<a href="${pageContext.request.contextPath}/przepisy">Lista przepisów</a>

</body>
</html>