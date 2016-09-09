<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ajax校验用户名</title>
<script type="text/javascript" src="js/my.js"></script>
<link type="text/css" href="css/myStyle.css" rel="Stylesheet" />
</head>
<body>
	<div id="form_div">
		<form action="" method="post" >
			<p>用户名：<input name="username" type="text" onblur="check(this.value)"/><sub id="name_msg"></sub></p>
			<p>密&emsp;码：<input name="pwd" type="password"/></p>
			<p>城&emsp;市：
				省<select id="provence" onchange="setCity(this.value)">
					<option value=""/>请选择
					<option value="jx"/>江西省
					<option value="zj"/>浙江省
				</select>&emsp;
				市<select id="citys">
					<option value=""/>请选择
				</select>
			</p>
			<p><input type="submit" value="注册"/>&emsp;<input type="reset" value="重置"/></p>
		</form>
	</div>
</body>
</html>