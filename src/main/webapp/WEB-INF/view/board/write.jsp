<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ��� �� ���</title>
</head>
<body>
<!-- ��ȿ�� �˻縦 ���� �𵨾�Ʈ����Ʈ�� -->
<form:form modelAttribute="board" action="write.shop" method="post" enctype="multipart/form-data" name="f">
		<table border="1" cellpadding="0" cellspacing="0">
			<caption>SPRING �Խ���</caption>
			<tr>
				<td align="center">�۾���</td>
				<td><form:input path="name" value="${sessionScope.loginUser.userId}" readonly="true" /><font color="red"><form:errors path="name" /></font>
				</td>
			</tr>
			<tr>
				<td align="center">��й�ȣ</td>
				<td><form:password path="pass" /><font color="red"><form:errors path="pass" /></font>
			</tr>
			<tr>
				<td align="center">����</td>
				<td><form:input path="subject" /><font color="red"><form:errors path="subject" /></font>
			</tr>
			<tr>
				<td align="center">����</td>
				<td><form:textarea rows="15" cols="80" path="content" /><font color="red"><form:errors path="content" /></font>
			</tr>
			<tr>
				<td align="center">÷������</td>
				<td><input type="file" name="file1"></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<a href="javascript:document.f.submit()">[�Խù����]</a>
				<a href="list.shop">[�Խù����]</a>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>