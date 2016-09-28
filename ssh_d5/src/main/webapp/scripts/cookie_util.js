
//获得coolie 的值

//function cookie(name){    
//
//   var cookieArray=document.cookie.split("; "); //得到分割的cookie名值对    
//
//   for (var i=0;i<cookieArray.length;i++){    
//
//      var arr=cookieArray[i].split("=");       //将名和值分开    
//
//      if(arr[0]==name)return unescape(arr[1]); //如果是指定的cookie，则返回它的值    
//
//   } 
//
//   return ""; 
//
//} 

 

/*function delCookie(name)//删除cookie

{

   document.cookie = name+"=;expires="+(new Date(0)).toGMTString();

}*/



function getCookie(objName){//获取指定名称的cookie的值

    var arrStr = document.cookie.split("; ");

    for(var i = 0;i < arrStr.length;i ++){

        var temp = arrStr[i].split("=");

        if(temp[0] == objName) return unescape(temp[1]);

   } 

}

 

function addCookie(objName,objValue,objHours){      //添加cookie

    var str = objName + "=" + escape(objValue);

    if(objHours > 0){                               //为时不设定过期时间，浏览器关闭时cookie自动消失

        var date = new Date();

        var ms = objHours*3600*1000;

        date.setTime(date.getTime() + ms);

        str += "; expires=" + date.toGMTString();

   }

   document.cookie = str;

}

 

var Days = 30; //此 cookie 将被保存 30 天
//设置cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(name)//取cookies函数        

{

    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));

     if(arr != null) return unescape(arr[2]); return null;

 

}

//清除cookie  
function delCookie(name) {  
    setCookie(name, "null", -1);  
}  

