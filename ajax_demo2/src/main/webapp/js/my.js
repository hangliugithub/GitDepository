/*
 * 将JSON字符串转换成JavaScript对象
 * 使用prototype这个js库
 */
function f1(){
	var str = '{"code":"6000015","name":"微软","price":16}';
	var obj = str.evalJSON();
	alert(obj.code+","+obj.name+","+obj.price);
}

function f2(){
	var str = '[{"code":"60000132","name":"微软we","price":16},{"code":"600001e","name":"微软se","price":143}]';
	var objs = str.evalJSON();
	alert(objs[1].code);
}