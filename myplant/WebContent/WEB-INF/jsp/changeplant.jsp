<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="text-align:center">草本植物浇灌系统</h1>
<h2 style="text-align:center">修改植物信息页面</h2>
<div align="center">
<form action="changeplantform" method="post">
<table>
		<tr>
			<td><label>植物ID：</label></td>
			<td><input type="text" name="plantId" value="${plant.plantId}" readonly="readonly"></td>
		</tr>
		<tr>
			<td><label>植物名称：</label></td>
			<td><input type="text" name="plantName" value="${plant.plantName}"/></td>
		</tr>
		<tr>
			<td><label>最低温度：</label></td>
			<td><input type="text" name="temperatureMin" value="${plant.temperatureMin}"/></td>
		</tr>
		<tr>
			<td><label>最高温度：</label></td>
			<td><input type="text" name="temperatureMax" value="${plant.temperatureMax}"/></td>
		</tr>
		<tr>
			<td><label>最低湿度：</label></td>
			<td><input type="text" name="moistureMin" value="${plant.moistureMin}"/></td>
		</tr>
		<tr>
			<td><label>最高湿度：</label></td>
			<td><input type="text" name="moistureMax" value="${plant.moistureMax}"/></td>
		</tr>
		<tr>
			<td><label>花语：</label></td>
			<td><input type="text" name="flowerLanguage" value="${plant.flowerLanguage}"/></td>
		</tr>
		<tr>
			<td><label>养护知识：</label></td>
			<td><textarea rows="3" name="maintenanceKnowledge">${plant.maintenanceKnowledge}</textarea></td>
		</tr>
		<tr>
			<td><label>喜水程度：</label></td>
			<td><input type="text" name="waterPreference" value="${plant.waterPreference}"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="提交"></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>