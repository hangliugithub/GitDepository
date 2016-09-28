<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Struts2 Demo</title>
</head>
<body>
	<h1>
		Hello Struts2!
	</h1>
	<hr>
	${message}
	<hr>
	${names }
	<c:if test="${not empty names }">
		<h2>姓名：</h2>
		<ul>
			<c:forEach items="${names }" var="name">
				<li>${name }</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>