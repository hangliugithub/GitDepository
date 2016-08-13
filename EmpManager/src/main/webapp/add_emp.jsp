<%@ 
	page language="java" 
	contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"
	import="dao.*,entity.*"
%>

<div id="addEmp">
		<p id="head">&emsp;增加员工&emsp;&emsp;&emsp;&emsp;&emsp;
			<sub id="sub"><em>存在 创造价值</em></sub></p>
		<form action="addEmp.do" method="post">
			<p>姓名：<input type="text" name="ename"></p>
			<p>职位：<input type="text" name="job"></p>
			<p>月薪：<input type="text" name="sal"></p>		
			<p><input type="submit" value="保存" class="btn">&emsp;
				<input type="reset" value="重置" class="btn">&emsp;
				<input type="button" value="退出" id="exit" class="btn">
			</p>
			<script type="text/javascript">
				$("#exit").click(function(){
					$("#addEmp").slideUp("slow");
					
				});
			</script>
		</form>
</div>

<%--	
	EmpDao dao1 = new EmpDao();
	Emp emp = new Emp();
	request.setCharacterEncoding("utf-8");
	emp.setEname(request.getParameter("ename"));
	emp.setJob(request.getParameter("job"));
	emp.setSal(Double.parseDouble(request.getParameter("sal")));
	if(dao1.add(emp)){
		response.sendRedirect("find_emp.jsp");
	}
--%>	