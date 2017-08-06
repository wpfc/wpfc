<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>创建客户</title>
</head>
<body>
	<h1>创建客户界面</h1>
	<form id="customer_form" enctype="multipart/form-data">
		<table>
			<tr>
				<td>客户名称：</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>联系人：</td>
				<td><input type="text" name="contact"></td>
			</tr>
			<tr>
				<td>电话号码：</td>
				<td><input type="text" name="telephone"></td>
			</tr>
			<tr>
				<td>照片：</td>
				<td><input type="file" name="photo"></td>
			</tr>
		</table>
		<button type="submit">提交</button>
	</form>

<script src="${BASE}/static/jquery-1.7.2.min.js" ></script>
<script src="${BASE}/static/jquery.form.js" ></script>
<script type="text/javascript">
$("#customer_form").ajaxForm({
	type : "post",
	url : "${BASE}/addCustomerPage",
	success : function(data){
		if(data){
			alert("success");
		}
	}
});
</script>
</body>
</html>