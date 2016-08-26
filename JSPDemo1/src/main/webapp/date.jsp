<%@ 
	page language="java" 
	contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"
	import="java.util.Date,java.text.SimpleDateFormat"
%>
<!-- pageEncoding:当前jsp文件编码 -->
<!-- contentType:jsp生成响应信息时通知浏览器响应数据的格式 -->
<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = sdf.format(date);
%>
<p id="time"><%=now %></p>