<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ٱ���</title>
</head>
<body>
<h2>��ٱ��� ��ǰ</h2>
<table>
<tr><td colspan="2"><font color="green">��ٱ��� ��ǰ ���</font></td></tr>
<tr><td>��ǰ��</td><td>����</td><td>����</td><td>�հ�</td></tr>

<c:set var="tot" value="${0}" /><!-- �հ� �ʱⰪ�� 0���� forEach�� �ٱ����� �ϳ� �־���Ѵ�. -->
<c:forEach items="${cart.itemList}" var="itemSet" varStatus="stat">
<tr><td>${itemSet.item.name}</td><td>${itemSet.item.price}</td>
<td>${itemSet.quantity}</td>
<td>${itemSet.quantity * itemSet.item.price}
<!-- index�� ����. index�� ����. ArrayList�� ������ �����ϴ� Ư¡���ִ�. ������ index������ List�� ������ ����. -->
<a href="cartDelete.shop?index=${stat.index}">��</a>
</td></tr>
<c:set var="tot" value="${tot + (itemSet.quantity*itemSet.item.price)}" />
</c:forEach>

<tr><td colspan="4" align="right"><font color="green">
�� ���Աݾ� : ${tot}��</font></td></tr></table>
<br>${message}<br><a href="../item/list.shop">��ǰ���</a>
<a href="checkout.shop">�ֹ��ϱ�</a>
</body>
</html>