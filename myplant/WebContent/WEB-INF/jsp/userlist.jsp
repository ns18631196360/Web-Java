<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
</head>
<body>
<h1 style="text-align:center">草本植物浇灌系统</h1>
<h2 style="text-align:center">用户列表</h2>
<div align="center">
<table border="1" cellpadding="10" cellspacing="0">
	<tr>
		<td>用户ID</td>
		<td>用户名</td>
		<td>用户密码</td>
		<td>用户手机号码</td>
		<td>用户性别</td>
		<td></td>
	</tr>
	<c:forEach items="${userList}" var="data">
	<tr>
		<td>${data.userId}</td>
		<td>${data.userName}</td>
		<td>${data.userPasswd}</td>
		<td>${data.userPhone}</td>
		<td>${data.userSex}</td>
		<td><a href="deleteuser?userId=${data.userId}"/>删除</td>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>