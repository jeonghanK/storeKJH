<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������</title>
<script type="text/javascript">
	function allchkbox(chk) {
		var chks = document.getElementsByName("idchks");
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked = chk.checked;
		}
	}
	function graph_open(url) {
		var op = "width=700,height=500,scrollbars=yes,left=50,top=150";
		window.open(url + ".shop", "graph", op);
	}
</script>
</head>
<body>
	<form action="mailForm.shop" method="post">
		<table border="1" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td colspan="7" align="center">ȸ�� ���</td>
			</tr>
			<tr>
				<td>���̵�</td>
				<td>�̸�</td>
				<td>��ȭ��ȣ</td>
				<td>����</td>
				<td>�̸���</td>
				<td>&nbsp;</td>
				<td><input type="checkbox" name="allchk"
					onchange="allchkbox(this)"></td>
			</tr>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.userId}</td>
					<td>${user.userName}</td>
					<td>${user.phoneNo}</td>
					<td><fmt:formatDate value="${user.birthDay}"
							pattern="yyyy-MM-dd" /></td>
					<td>${user.email}</td>
					<td><a href="../user/update.shop?id=${user.userId}">����</a> <a
						href="../user/delete.shop?id=${user.userId}">����</a> <a
						href="../user/mypage.shop?id=${user.userId}">ȸ������</a></td>
					<td><input type="checkbox" name="idchks"
						value="${user.userId}"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" align="center">
				<input type="submit" value="���Ϻ�����"> 
				<input type="button" value="�Խù��ۼ��׷�������(����)" onclick="graph_open('graph1')">
				<input type="button" value="�Խù��ۼ�wordCloud(����)" onclick="graph_open('graph2')"></td>
			</tr>
		</table>
	</form>
</body>
</html>