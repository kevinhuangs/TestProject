<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html >
<head></head>
<body onload="testajax()">
	Hello Ajax!<br>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
	function testajax(){
		fnGetAjaxReturn('http://localhost:8080/TestProject/TestServlet?a='+Math.random());
	}
	
	function fnGetAjaxReturn(url)
	{	
		var userAgent = navigator.userAgent;
		var http_request = false;
		//开始初始化XMLHttpRequest对象
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			http_request = new XMLHttpRequest();
			if (http_request.overrideMimeType) {//设置MiME类别
				http_request.overrideMimeType("text/xml");
			}
		}
		//else if (window.ActiveXObject) { // IE浏览器
		else if (window.ActiveXObject||userAgent.indexOf("Trident") > -1){
			try {
				http_request = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					http_request = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {alert("错了吧");}
			}
		}
		if (!http_request) { // 异常，创建对象实例失败
			window.alert("不能创建XMLHttpRequest对象实例.");
			return false;
		}
		http_request.open("GET", url, false);//true 异步  false 同步 
		http_request.send();
		alert("异步请求之后执行时间："+new Date +'\n毫秒数：'+ new Date().getMilliseconds());
	}
</script>