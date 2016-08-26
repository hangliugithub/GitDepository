<!-- 指令 -->
<%@page pageEncoding="utf-8"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>第一个JSP</title>
		<style type="text/css">
			#time{
				text-align:right;
				color:#888;
			}
		</style>
	</head>
	<body>
		<!-- 通过include指令引入date.jsp，相当于将其内容拷贝到此处 -->
		<%@include file="date.jsp" %>
		
		<!-- 3.JSP声明（简单了解） -->
		<%!
			public int sqrt(int n){
				return n*n;
			}
		%>
		<ul>
			<!-- 1.JSP小脚本 -->
			<%
				for(int i=0;i<10;i++){
			%>	
				<!-- 2.JSP表达式 -->
				<li><%=sqrt((int)(Math.random()*100+1)) %></li>
			<%	
				}
			%>
		</ul>
		
		
	</body>
</html>