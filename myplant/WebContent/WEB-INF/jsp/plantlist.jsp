<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>植物列表</title>
</head>
<body>
<h1 style="text-align:center">草本植物浇灌系统</h1>
<h2 style="text-align:center">植物列表</h2>
<div align="center">
<table border="1" cellpadding="10" cellspacing="0">
	<tr>
		<td>植物ID</td>
		<td>植物名称</td>
		<td>最低温度</td>
		<td>最高温度</td>
		<td>最低湿度</td>
		<td>最高湿度</td>
		<td>花语</td>
		<td>养护知识</td>
		<td>喜水程度</td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<c:forEach items="${plantList}" var="data">
	<tr>
		<td>${data.plantId }</td>
		<td>${data.plantName }</td>
		<td>${data.temperatureMin }</td>
		<td>${data.temperatureMax }</td>
		<td>${data.moistureMin }</td>
		<td>${data.moistureMax }</td>
		<td>${data.flowerLanguage }</td>
		<td>${data.maintenanceKnowledge }</td>
		<td>${data.waterPreference }</td>
		<td><a href="changeplant?plantId=${data.plantId}"/>修改</td>
		<td><a href="deleteplant?plantId=${data.plantId}"/>删除</td>
		<td><a href="newplant"/>新建</td>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>