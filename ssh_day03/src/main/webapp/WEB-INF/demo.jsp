<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>value stack</title>
</head>
<body>
	<h1>ValueStack</h1>
	<p>s:debug用于查看value stack信息的</p>
	<div><s:debug/></div>
	<div><s:property value="message"/></div>
	<div><s:property value="name"/></div>
	<div><s:property value="#session.user"/></div>
	<div><s:action name="demo"></s:action></div>
</body>
</html>