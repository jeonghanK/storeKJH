<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Ż�� Ȯ�� ȭ��</title>
</head>
<body>
   <table border="1" width="100%">
      <tr><td>���̵�</td><td>${user.userId}</td></tr>
      <tr><td>�̸�</td><td>${user.userName}</td></tr>
      <tr><td>�����ȣ</td><td>${user.postcode}</td></tr>
      <tr><td>��ȭ��ȣ</td><td>${user.phoneNo}</td></tr>
      <tr><td>�ּ�</td><td>${user.address}</td></tr>
      <tr><td>�̸���</td><td>${user.email}</td></tr>
      <tr><td>�������</td><td><fmt:formatDate value="${user.birthDay}"
            pattern="yyyy-MM-dd"/></td></tr>
   </table><br>
   <form action="delete.shop" method="post" name="deleteform">
      <input type="hidden" name="id" value="${param.id}">
      ��й�ȣ<input type="password" name="password" size="12">
      <a href="javascript:document.deleteform.submit()">[ȸ��Ż��]</a>&nbsp;
   </form></body>
</html>



