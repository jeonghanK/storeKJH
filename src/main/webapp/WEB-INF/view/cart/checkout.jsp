<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�ֹ� �� ��ǰ ��� ����</title>
<style type="text/css">
table {
	width: 90%;
	border-collapse: collapse;
}

th, td {
	border: 3px solid #bcbcbc;
	text-align: center;
	padding: 8px;
}

th {
	background-color: #4caf50;
	color: white;
	text-align: center;
}

td {
	background-color: #f2f2f2;
}

td.title {
	background-color: #e2e2e2;
	color: blue;
}
</style>
</head>
<body>
<h2>����� ����</h2>
<table>
<tr><td width="30%" class="title">������ID</td>
<td width="70%">${loginUser.userId}</td></tr>
<tr><td width="30%" class="title">�̸�</td>
<td width="70%">${loginUser.userName}</td></tr>
<tr><td width="30%" class="title">�����ȣ</td>
<td width="70%">${loginUser.postcode}</td></tr>
<tr><td width="30%" class="title">�ּ�</td>
<td width="70%">${loginUser.address}</td></tr>
<tr><td width="30%" class="title">��ȭ��ȣ</td>
<td width="70%">${loginUser.phoneNo}</td></tr>
<tr><td width="30%" class="title">�̸���</td>
<td width="70%">${loginUser.email}</td></tr>
</table><br><br>
<h2>��ٱ��� ���</h2>
<table>
<tr><th>��ǰ��</th><th>��ǰ����</th><th>����</th><th>��ǰ�հ�</th></tr>
<!-- ���� ��� ���� ���ǵ��� CART�� �����س���. �װ͵��� ����Ʈ�� �����ͼ� itemSet�̶�� ������ ���� -->
<!-- Ǯ������ sessionScope.CART.itemList -->
<c:forEach items="${CART.itemList}" var="itemSet">
<tr><td>${itemSet.item.name}</td><td>${itemSet.item.price} ��</td>
    <td>${itemSet.quantity}��</td>
    <td>${itemSet.quantity * itemSet.item.price}</td>
</tr></c:forEach>
<tr><td colspan="4" style="text-align:right;">�� �ݾ� :
<fmt:formatNumber value="${CART.totalAmount}" pattern="#,###" />��<!-- ���� �� ���ǵ��� �� �հ� -->
</td></tr>
<tr><td colspan="4">
<a href="end.shop">Ȯ��</a>&nbsp;
<a href="../item/list.shop">��ǰ���</a>
</td></tr>
</table>
</body>
</html>