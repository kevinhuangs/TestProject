<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<%
String iUrl=request.getRequestURL().toString().substring(0,request.getRequestURL().toString().lastIndexOf("/"));
String viewId = request.getParameter("viewId");
String subjectId = request.getParameter("subjectId");
%>


	<FORM  NAME="uploadForm" METHOD='POST' 
	action="<%=iUrl%>/import4GZWCQK.jsp?subjectId=<%=subjectId %>&viewId=<%=viewId %>" 
	ENCTYPE="multipart/form-data" onSubmit="return viewlist.uploadCheck(this);"
	target="importFrame"><!--importFrame-->
		  <tr bgcolor="#F4F4F4" align="left">
				<td>
				请选择导入文件:&nbsp;<input type=file name="filename">
			  <input type=button name="f" value="导入数据" onclick="uploadCheck()"></td>
		  </tr>
</form>
<script>
	function uploadCheck(f){
		var s=uploadForm.filename.value;
		if(s==""){
		  alert("请选择文件1"); 
		  return false;
		}else if("xlsx"!=s.substr(s.length-4,4)){
			alert("请选择扩展名为xlsx的文件");
			return false;
		}

		uploadForm.submit();
	}

	//var cururl = window.location.href;
	//alert("cururl:"+cururl);
</script>
<script>
	function test(){
		alert();
	}

</script>

<iframe name="importFrame" width="100%" height="0" src="" FRAMEBORDER=NO ></iframe>

	
</body>
</html>