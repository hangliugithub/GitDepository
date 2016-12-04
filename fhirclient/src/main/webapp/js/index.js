
var contextData = {
		serverBase:''
};

var model = {
		obj:{},
		objs:[]
}

$(function(){
	$("#nav_div").css("height",window.parent.document.documentElement.clientHeight-100);
	$("#content_div").css("height",window.parent.document.documentElement.clientHeight-100);
	
	$("input:radio").click(getPage);
	$.getJSON("patient/getContext.do?nocache="+new Date().getTime(),function(result){
		if(result.state==0){
			console.log(result);
			$("#serv").val(result.data.server);
			$("#dataFormat").children().eq(result.data.type+0).attr("selected","selected");
		}
		getPage();
	});
	$("#serv").keypress(function(event){
		//console.log(keycode);
		if(event.which==13){
			changservicebase();
		}
	});
});

function getPage(){
	var resourceType = $('input:radio:checked').val();
	//alert(resourceType);
	$("#content_div").load(resourceType+".html?nocache="+new Date().getTime(),function(){
		changedatatype();
		changeserverbase();
	});
}