<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>수정 게시판</title>
	<style>
		td { aligin: center; }
	</style>
	<script>
		function file_delete() {
			document.f.file2.value=""; // file1 정보 지우기
			file_desc.style.display = "none";	// 파일 내용이 안보이게 됨
		}
	</script>
</head>
<body>
    <!-- board 객체를 받아와 넣기때문에 form: 일때 각 input에 value값들은 이미 넘어와서 따로 선언할 필요가없다. -->
	<form:form modelAttribute="board" action="update.shop?pageNum=${param.pageNum}" method="post" enctype="multipart/form-data" name="f">
		<input type="hidden" name="num" value="${board.num }"><!-- 히든값으로 해당 게시물의 번호를 가지고있음 -->
		
		<!-- file2는 파라미터로 넘어오기 때문에 받아온 board 객체에는 존재하지않아서 유효성 검사에 걸려 다시 처음부터 board 객체를 받아오면 file2부분인, 첨부파일이 날라가게됨 -->
		<!-- 컨트롤러에서 mav안에 dbBoard 객체를 추가해줘야 db안의 기존 file까지 가져올 수 있다 -->
		<input type="hidden" name="file2" value="${board.fileurl }">
		<table border="1" cellpadding="0" cellspacing="0">
			<caption>수정 게시판</caption>
			<tr>
				<td>글쓴이</td>
				<td><form:input path="name" /><font color="red"><form:errors path="name" /></font>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><form:password path="pass" /><font color="red"><form:errors path="pass" /></font>
			</tr>
			<tr>
				<td>제목</td>
				<td><form:input path="subject" /><font color="red"><form:errors path="subject" /></font></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><form:textarea rows="15" cols="80" path="content" /><font color="red"><form:errors path="content" /></font>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><c:if test="${!empty board.fileurl }">
					<div id="file_desc">
						<a href="file/${board.fileurl }">${board.fileurl }</a>
						<a href="javascript:file_delete()">[첨부파일삭제]</a>
					</div>
					</c:if>
					<input type="file" name="file1">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<a href="javascript:document.f.submit()">[게시물수정]</a>
				<a href="list.shop?pageNum=${param.pageNum}">[목록으로 돌아가기]</a>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>