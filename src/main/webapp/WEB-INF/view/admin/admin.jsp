<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>관리자</title>
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
				<td colspan="7" align="center">회원 목록</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>전화번호</td>
				<td>생일</td>
				<td>이메일</td>
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
					<td><a href="../user/update.shop?id=${user.userId}">수정</a> <a
						href="../user/delete.shop?id=${user.userId}">삭제</a> <a
						href="../user/mypage.shop?id=${user.userId}">회원정보</a></td>
					<td><input type="checkbox" name="idchks"
						value="${user.userId}"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" align="center">
				<input type="submit" value="메일보내기"> 
				<input type="button" value="게시물작성그래프보기(파이)" onclick="graph_open('graph1')">
				<input type="button" value="게시물작성wordCloud(파이)" onclick="graph_open('graph2')"></td>
			</tr>
		</table>
	</form>
</body>
</html>