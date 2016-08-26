<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>测试您的BMI指数</title>
		<style type="text/css">
			body{
				background:rgb(233,233,233);
				font-family:"微软雅黑";
			}
			div{
				margin:0 auto;
				background:#fff;
			}
			p{
				padding:10px;
				margin:0;
				text-align:center;
			}
			#res p{
				text-align:left;
			}
			h3{
				border-left:2px solid rgb(63,72,204);
				margin-left:10px;
				padding-left:5px;
				font-family:"宋体";
			}
			#panel{
				width:340px;
				border:1px solid rgb(63,72,204);
				box-shadow:0 3px 5px 2px #888;
			}
			#res{
				width:340px;
				display:none;
				border:0;
				color:#fff;
				background:rgb(106,184,84);
			}
			#calcBtn{
				border:0;
				background:rgb(63,72,204);
				font-family:"微软雅黑";
				color:#fff;
				width:280px;
				height:2em;
				margin:5px auto;
				border-radius:2px;
			}
			#bmi{
				font-size:30px;
				font-weight:bold;
			}
		</style>
		<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
		<script type="text/javascript">
			$(function(){
				var bmi = $("#bmi").html();
				if(bmi){
					bmi = Number(bmi).toFixed(2);
					$("#bmi").html(bmi);
					$("#res").slideDown("1000");
				}
				$("form").submit(check);
				$("input:text").focus(function(){$(event.target).val("")});
			});
			/*校验输入的数据是否正确*/
			function check(){
				var $inputs = $("input:text");
				var height = $("input:text").eq(0).val();
				var weight = $("input:text").eq(1).val();
				console.log(height+":"+weight);
				if(!height || !weight){
					alert("输入的数据不能为空！");
					return false;
				}else if(isNaN(height) || isNaN(weight) || height<=0 || weight<=0){
					alert("请输入合法数据！");
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body>
		<div id="panel">
			<h3>计算你的BMI指数</h3>
			<form action="calc.do" method="post">
				<p>
					身高：<input type="text" name="height" value="${info.height }"/>
					<sub>m</sub>
				</p>
				<p>
					体重：<input type="text" name="weight" value="${info.weight }"/>
					<sub>kg</sub>
				</p>
				<p><input id="calcBtn" type="submit" value="计算"/></p>
			</form>
			<div id="res">
				<p>你的BMI值是：<span id="bmi">${info.bmi }</span></p>
				<p>身体状态：<b>${info.type }</b></p>
			</div>
		</div>
	</body>
</html>