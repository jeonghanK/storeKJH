<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ǰ���ȭ��</title>
</head>
<body>
	<form:form modelAttribute="item" action="register.shop"
		enctype="multipart/form-data">
		<h2>��ǰ ��� ȭ��</h2>
		<table>
			<tr>
				<td>��ǰ��</td>
				<td><form:input path="name" maxlength="20" /></td>
				<td><font color="red"><form:errors path="name" /></font></td>
			</tr>
			<tr>
				<td>����</td>
				<td><form:input path="price" maxlength="6" /></td>
				<td><font color="red"><form:errors path="price" /></font></td>
			</tr>
			<tr>
				<td>��ǰ�̹���</td>
				<td colspan="2"><input type="file" name="picture" /></td>
			</tr>
			<tr>
				<td>��ǰ����</td>
				<td><form:textarea path="description" cols="20" rows="5" /></td> <!-- 20 ����, 5�� ���� -->
				<td><font color="red"><form:errors path="description" /></font></td>
			</tr>
			<tr><td colspan="3">
			<input type="submit" value="���">&nbsp;
			<input type="button" value="��ǰ���" onclick="location.href='list.shop'">
			</td></tr>
		</table>
	</form:form>
</body>
</html>