<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
	<head>
		<title>用户登录</title>
		<meta charset="utf-8"/>
		<style type="text/css">
			body{
				background:rgb(233,233,233);
			}
			#login{
				font-family:"微软雅黑";
				font-size:15px;
				width:300px;
				height:150px;
				margin:20px auto;
				padding:20px;
				background:#fff;
			}
		</style>
	</head>
	<body>
		<div id="login">
			<form action="login9.do" method="post">
				<p>账号：<input type="text" name="userCode"/></p>
				<p>密码：<input type="password" name="password"/></p>
				<p><input type="submit" value="登录"/>&emsp;<input type="reset" value="重置"/></p>
			</form>
		</div>
	</body>
</html>