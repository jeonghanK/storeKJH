<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<%-- /WebContent/model1/board/replyForm.jsp
	1. 원글의 정보 : num, ref, reflevel, refstep
	2. 답변글로 입력 된 정보 
 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시판 답글 쓰기</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
td {
	aligin: center;
}
</style>
</head>
<body>
	<form:form action="reply.shop?pageNum=${param.pageNum}" method="post" name="f" modelAttribute="board">
		<input type="hidden" name="num" value="${board.num }">
		<input type="hidden" name="ref" value="${board.ref }">
		<input type="hidden" name="reflevel" value="${board.reflevel }">
		<input type="hidden" name="refstep" value="${board.refstep }">

		<table class="w3-table-all w3-border" border="1" cellpadding="0"
			cellspacing="0">
			<tr>
				<td colspan="2">답글 작성</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td><input type="text" name="name" value="${sessionScope.loginUser.userId}" readonly />
				<font color="red"><form:errors path="name" /></font></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass">
				<font color="red"><form:errors path="pass" /></font></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="Re:${board.subject }" />
				<font color="red"><form:errors path="subject" /></font></td>
			</tr>
			<tr>
				<td align="center">내용</td>
				<td><textarea rows="15" cols="80" name="content"></textarea>
				<font color="red"><form:errors path="content" /></font></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<a href="javascript:document.f.submit()">[답변등록]</a> 
				<a href="javascript:document.f.reset()">[다시작성]</a> 
				<a href="javascript:history.go(-1)">[뒤로가기]</a>
			</tr>
		</table>
		</form:form>
</body>
</html>