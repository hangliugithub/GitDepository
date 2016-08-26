<%@page pageEncoding="utf-8"
	import="java.util.*,dao.*,entity.*"
%>
<html>
	<head>
		<title>查询员工</title>
		<meta charset="utf-8">
		<style type="text/css">
			body,html{
				background:rgb(242,242,242);
				font-family:"微软雅黑";
			}
			div{
				width:60%;
				box-shadow:0 3px 5px 2px #888;
				background:#fff;
				padding:10;
				margin:0 auto;
			}
			table{
				
				border-collapse:collapse;
				width:80%;
				margin:0 auto;
			}
			td,th{
				border:1px solid #000;
			}
			th{
				background:rgb(65,150,250);
				height:30px;
				color:#fff;
			}
			p{
				margin:0;
			}
			#head1{
				background:rgb(65,132,243);
				color:#fff;
				height:55px;
				padding-top:0;
				padding-bottom:0;
			}
			#p1,#p2{
				width:40%;
				float:left;
				font-size:18px;
				font-weight:bold;
				line-height:55px;
				padding:0 10px;
			}
			#p2{
				float:right;
				text-align:right;
			}
			.btn{
				margin-top:16px;
				border:0;
				background:rgb(242,242,242);
				color:#000;
				width:50px;
				height:2em;
				border-radius:2px;
				box-shadow:1px 3px 2px #00f;
				
			}
			.btn:hover{
				cursor: pointer;
				position:relative;
				top:2px;
				left:1px;
				box-shadow:0 0 0;
			}
			#content{
				position:relative;
			}
			#head{
				margin:0;
				height:50px;
				font-size:20px;
				font-wight:bold;
				background:rgb(65,132,243);
				color:#fff;
				line-height:50px;
			}
			#sub{
				font-size:10px;
				font-weight:normal;
				margin-bottom: 5px;
			}
			form{
				padding:10px;
				text-align:center;
			}
			#addEmp{
				width:300px;
				height:250px;
				margin:100px auto;
				padding:0;
				background:#fff;
				/*border-radius:5px;*/
				position:relative;
				box-shadow:0 3px 5px 2px #888;
				display:none;
		  		position: absolute;
		  		left:250px;
		  		
			}
		</style>
		<script type="text/javascript" src="jquery-3.1.0.js"></script>
		
	</head>
	<body>
		<div id="head1">
			<p id="p1">所有员工信息</p>
			<p id="p2"><input class="btn" id="add" type="button" value="增加"></p>
			<script type="text/javascript">
				$("#add").click(function(){
					console.log("hello");
					$("#addEmp").slideDown("solw");
				});
			</script>
			
		</div>
		<div id="content">
		<%@ include file="add_emp.jsp" %>
		<table>
			<thead>
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>职位</th>
				<th>月薪</th>
			</tr>
			</thead>
			<tbody>
			<%
				EmpDao dao = new EmpDao();
				List<Emp> list = dao.findAll();
				if(list!=null){
					for(Emp e:list){
			%>
						<tr>
							<td><%=e.getEmpno() %></td>
							<td><%=e.getEname() %></td>
							<td><%=e.getJob() %></td>
							<td><%=e.getSal() %></td>
						</tr>
			<%
					}
				}
			%>
			</tbody>
		</table>
		<script type="text/javascript">
			$("tbody tr:even").css("background","rgb(242,242,242)");
		</script>
		</div>
	</body>
</html>
