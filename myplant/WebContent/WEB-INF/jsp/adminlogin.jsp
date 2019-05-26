<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员登录页面</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/cufon-yui.js"></script>
<script type="text/javascript" src="/js/arial.js"></script>
<script type="text/javascript" src="/js/cuf_run.js"></script>
</head>
<body>
  <h1 style="text-align:center">草本植物浇灌系统</h1>
  <h2 style="text-align:center">管理员登录</h2>
  <div align="center">
<form action="adminloginform" method="post">
	<table>
		<tr>
			<td><label>用户名：</label></td>
			<td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td><label>密码：</label></td>
			<td><input type="text" name="password"/></td>
		</tr>
		<tr>
			<td><input align="right" type="submit" value="登录"></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>