<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<body>
	<h1>登录系统</h1>
	<c:if test="${not empty message }">
		<h2>${message }</h2>
	</c:if>
	<hr>
	<div>
		<c:url var="act" value="/demo/login.action"/>
		<form action="${act}" method="post">
			<div>
				<label>用户名：</label>
				<input type="text" name="username" value="${username}">
			</div>
			<br>
			<div>
				<label>密&emsp;码：</label>
				<input type="password" name="password">
			</div>
			<br>
			<div><input type="submit" value="登录">&emsp;<input type="reset" value="重置"></div>
		</form>
	</div>
</body>
</html>