<%@ page pageEncoding="utf-8" %>
<img src="./images/logo.png" alt="logo" class="left"/>
<!-- EL默认从四个对象中取值，不包括cookie。
	想从cookie中取值，必须满足以下格式：cookie.key.value 其中key根据你的设置来做出改变，其他固定不变 -->
<!-- 从Cookie中获取账户名 -->
<%-- 
<a href="#"><span style="color:#00f;">欢迎您,${cookie.adminCode.value }</span>&emsp;[退出]</a>
--%>

<!--  从session获取账户名-->
<span style="color:#fff;font-size:15px;">欢迎您,${admin.adminCode }</span>
<a href="#" onclick="javascript:location.href='/netctoss-springmvc/logout.do';">[退出]</a>