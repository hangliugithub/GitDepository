/*获取xhr对象*/
function getXhr() {
	var xhr = null;
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest;
	} else {
		xhr = new ActiveXObject("Microsoft.XMLHttp");
	}
	return xhr;
}

function check(name) {
	var xhr = getXhr();
	console.log(name);
	if (name) {
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("name_msg").innerHTML = xhr.responseText;
			}
		};
		xhr.open("post", "check_name.do", true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		document.getElementById("name_msg").innerHTML = "正在检查。。。";
		xhr.send("uname=" + name);
	}
}

function setCity(province){
	var xhr = getXhr();
	if(province){
		xhr.onreadystatechange = function(){
			if (xhr.readyState == 4 && xhr.status == 200) {
				var citys = document.getElementById("citys");
				citys.innerHTML = "";
				var resText = xhr.responseText;
				var citysText = resText.split(",");
				for(var i=0;i<citysText.length;i++){
					var city = document.createElement("option");
					city.innerText=citysText[i].split("=")[0];
					city.value=citysText[i].split("=")[1];
					citys.appendChild(city);
				}
			}
			
		};
		xhr.open("post","setCity.do",true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.send("province=" + province);
	}else{
		document.getElementById("citys").innerHTML="<option>请选择</option>";
	}
	
	
}









