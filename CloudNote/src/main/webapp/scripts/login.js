/*
 * script/login.js
 * 登录界面中执行的脚本程序
 */
//console.log("hehe");
/*网页加载以后执行*/
$(function(){
	//为login按钮绑定事件
	$('#login').click(loginAction);
	//为regist按钮绑定事件
	$('#regist_button').click(registAction);
	$('#codeImg').click(function(){
		$(this).attr('src','account/code.do?'+new Date().getTime());
	});
	
	$('#code').blur(function(){
		var code = $(this).val();
		var url = baseUrl+'/account/checkCode.do?code='+code;
		$.getJSON(url,function(result){
			if(result.state==SUCCESS){
				console.log(code);
				$('#code').removeClass('error');
			}else{
				$('#code').addClass('error');
			}
		});
	});
});
// 移到const.js中了
// var SUCCESS = 0;
// var ERROR = 1;

function loginAction(){
//	console.log("login click");
	//检查表单数据的正确性
	//将表单数据发送到服务器
	//利用callback处理返回结果
	//如果成功就跳转到首页
	//失败则显示错误消息
	var name=$('#count').val();
	var password = $('#password').val();
	var code = $('#code').val();
//	console.log(name+":"+password);
	var reg= /^\w{3,10}/;
	$('#count').removeClass('error');
	$('#password').removeClass('error');
	var pass = true;
	if(!reg.test(name)){
		$('#count').addClass('error');
		pass = false;
	}
	if(! reg.test(password)){
		$('#password').addClass('error');
		pass = false;
	}
	if(!code){
		$('#code').addClass('error');
		pass = false;
	}
	if(!pass){
		return false;
	}
	
	var data = {'name':name,'password':password,'code':code};
	console.log(data);
	
	$.ajax({
		url:'account/login.do',
		type:'post',
		dataType:'JSON',
		data:data,
		success:function(result){
//			console.log(result);
			if(result.state==SUCCESS){
				//登录成功将用户信息保存到cookie中
				var user = result.data;
//				$.cookie('userId', user.id, {expires:7, path:'/', secure: true});
//				$.cookie('userName', user.name, {expires:7, path:'/', secure: true});
//				$.cookie('userNick', user.nick, {expires:7, path:'/', secure: true});
				setCookie('userId',user.id,7);
				setCookie('userName',user.name,7);
				setCookie('userNick',user.nick,7);
				window.location='edit.html';
			}else{
				alert(result.message);
			}
		}
	});
}

function registAction(){
	var name = $('#regist_username').val();
	var nick = $('#nickname').val();
	var password = $('#regist_password').val();
	var confirm = $('#final_password').val();
	
	var nameReg = /^\w{3,10}$/;
	var nickReg = /^.{3,10}$/;
	
	var pass = true;
	$('.warning').hide();
	$('input').removeClass("error");
	if(!nameReg.test(name)){
		pass = false;
		$('#regist_username').addClass('error').next().show().children().eq(0).html("<sub>3~10个单词字符</sub>");
	}
	
	if(!nickReg.test(nick)){
		pass = false;
		$('#nickname').addClass('error').next().show().children().eq(0).html("<sub>3~10个字符</sub>");
	}
	
	if(!nameReg.test(password)){
		pass = false;
		$('#regist_password').addClass('error').next().show().children().eq(0).html("<sub>3~10个单词字符</sub>");
	}
	
	if(!nameReg.test(confirm)){
		pass = false;
		$('#final_password').addClass('error').next().show().children().eq(0).html("<sub>3~10个单词字符</sub>");
	}
	
	if(password!=confirm){
		pass = false;
		$('#final_password').addClass('error').next().show().children().eq(0).html("<sub>密码不一致</sub>");
	}
	
	if(!pass){
		return false;
	}
	
	var data = {'name':name,'password':confirm,'nick':nick};
	console.log(data);
	
	$.ajax({
		url:'account/regist.do',
		type:'post',
		dataType:'json',
		data:data,
		success:function(result){
			if(result.state==SUCCESS){
				alert("恭喜你，注册成功！");
				$('#count').val(result.data.name);
				$('#password').focus();
				$('#back').click();
			}else{
				//alert(result.message);
				$('#regist_username').addClass('error').next().show().children().eq(0).html(result.message);
			}
		}
	});
}




