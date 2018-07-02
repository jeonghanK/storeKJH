<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시물 상세 보기</title>
</head>
<body>
	<input type="hidden" name="num" value="${param.num }">
	<input type="hidden" name="pageNum" value="${param.pageNum }">
	<table align="center" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="2" align="center">Spring 게시판</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${board.name}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${board.subject}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<table border="0" width="490" height="250">
					<tr>
						<td>${board.content}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>&nbsp; <c:if test="${!empty board.fileurl }">
					<a href="file/${board.fileurl}">${board.fileurl}</a>
				</c:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a
				href="reply.shop?num=${board.num}&pageNum=${param.pageNum}">[답변]</a>
				<a href="update.shop?num=${board.num}&pageNum=${param.pageNum}">[수정]</a>
				<a href="delete.shop?num=${board.num}&pageNum=${param.pageNum}">[삭제]</a>
				<a href="list.shop?pageNum=${param.pageNum}">[목록]</a></td>
		</tr>
	</table>
</body>
</html>