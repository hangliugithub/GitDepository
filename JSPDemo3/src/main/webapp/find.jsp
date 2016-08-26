<%@
	page pageEncoding="utf-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="jessehu1520@outlook.com" prefix="t" %>
<!doctype html>
<html>
	<head>
		<title>EL+JSTL</title>
		<meta charset="utf-8">
	</head>
	<body>
		<h1>EL</h1>
		<hr>
		<!-- 1.获取Bean属性 -->
		<!-- request.getAttribute("stu").getName() -->
		<p>姓名：${student.name}</p>
		<p>年龄：${student["age"]}</p>
		<!-- request.getAttribute("student").getCourse().getId() -->
		<p>课程ID：${student.course.id}</p>
		<!-- request.getAttribute("student").getInterests()[1] -->
		<p>兴趣：${student.interests[1] }</p>
		<!-- 取值规则：
				EL默认从四个隐含对象取值，这些对象也叫做EL的取值范围:
				page,request,session,application
				EL 默认按照以下顺序依次取值 
				page.getAttribute("student")
				request.getAttribute("student")
				session.getAttribute("student")
				application.getAttribute("student")
				也可以强制从某个范围/对象内取值：
				sessionScope.student.name
		-->
		<p>性别：${sessionScope.student.sex }</p>		
		<!-- 2.进行运算 -->
		<p>年龄+5：${student.age+5 }</p>
		<p>20~30之间：${student.age>20 && student.age<30 }</p>
		<p>判空：${empty student }</p>
		<p>判空：${student==null }</p>
		<!-- 3.获取请求参数 -->
		<!-- request.getParameter("user") -->
		<p>参数：${param.user }</p>
		<br>
		<br>
		<h1>JSTL</h1>
		<hr>
		<!-- 1.if -->
		<p>	性别：
			<c:if test="${student.sex=='M'}">男</c:if>
			<c:if test="${student.sex=='F'}">女</c:if>
		</p>
		<!-- 2.choose -->
		<p>GENDER：
			<c:choose>
				<c:when test="${student.sex=='M' }">男</c:when>
				<c:otherwise>女</c:otherwise>
			</c:choose>
		</p>
		<!-- 3.forEach -->
		<p>INTERESTS:
			<c:forEach var="interest" items="${student.interests }">
				${interest }
			</c:forEach>
		</p>
		
		<!-- 4.自定义标签 -->
		<p><t:sysdate/></p>
	</body>
</html>













