<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ǰ ���� Ȯ��</title>
</head>
<body>
<h2>��ǰ ���� Ȯ��</h2>
<table>
<tr><td><img src="../picture/${item.pictureUrl}" width="200" height="250"></td></tr>
<td align="center">
<table>
<tr><td width="80">��ǰ��</td><td width="160">${item.name}</td></tr>
<tr><td width="80">����</td><td width="160">${item.price}</td></tr>
<tr><td width="80">��ǰ����</td><td width="160">${item.description}</td></tr>
<tr><td colspan="2">
<%-- �����ϱ� : id�� �ش��ϴ� ��ǰ ������ �����ϱ�.
                             ���� ������ list.shop����
                             ���� ���н� confirm.shop
 --%>
<input type="button" value="����" onclick="location.href='delete.shop?id=${item.id}'">&nbsp;
<input type="button" value="��ǰ���" onclick="location.href='list.shop'">
</td></tr>
</table>
</td>
</table>
</body>
</html>