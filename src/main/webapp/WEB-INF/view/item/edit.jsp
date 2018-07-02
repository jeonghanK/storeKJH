<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품 수정</title>
</head>
<body>
	<h2>상품 수정</h2>
	<form:form modelAttribute="item" action="update.shop" enctype="multipart/form-data">
		<form:hidden path="id" />
		<form:hidden path="pictureUrl" />
		<table>
			<tr>
				<td>상품명</td>
				<td><form:input path="name" /></td>
				<td><font color="red"><form:errors path="name" /></font></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><form:input path="price" /></td>
				<td><font color="red"><form:errors path="price" /></font></td>
			</tr>
			<tr>
				<td>상품이미지</td>
				<td><input type="file" name="picture" /></td>
				<td>&nbsp;${item.pictureUrl}</td>
			</tr>
			<tr>
				<td>상품개요</td>
				<td><form:textarea path="description" cols="30" rows="5"/></td>
				<td><font color="red"><form:errors path="description" /></font></td>
			</tr>
			<tr><td colspan="3"><input type="submit" value="수정" />
			<input type="button" value="상품목록" onclick="location.href='list.shop'">
			</td></tr>
		</table>
	</form:form>
</body>
</html>