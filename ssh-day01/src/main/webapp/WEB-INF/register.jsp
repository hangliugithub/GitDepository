<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
</head>
<body>
	<h1>用户注册</h1>
	<c:if test="${not empty message }">
		<h2>${message }</h2>
	</c:if>
	<hr>
	<div>
		<c:url var="act" value="/demo/register.action"/>
		<form action="${act}" method="post">
			<div>
				<label>用户名：</label>
				<input type="text" name="user.name" value="${user.name}">
			</div>
			<br>
			<div>
				<label>密&emsp;码：</label>
				<input type="password" name="user.password">
			</div>
			<br>
			<div>
				<label>昵&emsp;称：</label>
				<input type="text" name="user.nick" value="${user.nick}">
			</div>
			<br>
			<div>
				<label>年&emsp;龄：</label>
				<input type="text" name="user.age" value="${user.age}">
			</div>
			<br>
			<div><input type="submit" value="注册">&emsp;<input type="reset" value="重置"></div>
		</form>
	</div>
</body>
</html>