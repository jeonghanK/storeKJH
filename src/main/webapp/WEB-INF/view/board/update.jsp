<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>���� �Խ���</title>
	<style>
		td { aligin: center; }
	</style>
	<script>
		function file_delete() {
			document.f.file2.value=""; // file1 ���� �����
			file_desc.style.display = "none";	// ���� ������ �Ⱥ��̰� ��
		}
	</script>
</head>
<body>
    <!-- board ��ü�� �޾ƿ� �ֱ⶧���� form: �϶� �� input�� value������ �̹� �Ѿ�ͼ� ���� ������ �ʿ䰡����. -->
	<form:form modelAttribute="board" action="update.shop?pageNum=${param.pageNum}" method="post" enctype="multipart/form-data" name="f">
		<input type="hidden" name="num" value="${board.num }"><!-- ���簪���� �ش� �Խù��� ��ȣ�� ���������� -->
		
		<!-- file2�� �Ķ���ͷ� �Ѿ���� ������ �޾ƿ� board ��ü���� ���������ʾƼ� ��ȿ�� �˻翡 �ɷ� �ٽ� ó������ board ��ü�� �޾ƿ��� file2�κ���, ÷�������� ���󰡰Ե� -->
		<!-- ��Ʈ�ѷ����� mav�ȿ� dbBoard ��ü�� �߰������ db���� ���� file���� ������ �� �ִ� -->
		<input type="hidden" name="file2" value="${board.fileurl }">
		<table border="1" cellpadding="0" cellspacing="0">
			<caption>���� �Խ���</caption>
			<tr>
				<td>�۾���</td>
				<td><form:input path="name" /><font color="red"><form:errors path="name" /></font>
			</tr>
			<tr>
				<td>��й�ȣ</td>
				<td><form:password path="pass" /><font color="red"><form:errors path="pass" /></font>
			</tr>
			<tr>
				<td>����</td>
				<td><form:input path="subject" /><font color="red"><form:errors path="subject" /></font></td>
			</tr>
			<tr>
				<td>����</td>
				<td><form:textarea rows="15" cols="80" path="content" /><font color="red"><form:errors path="content" /></font>
			</tr>
			<tr>
				<td>÷������</td>
				<td><c:if test="${!empty board.fileurl }">
					<div id="file_desc">
						<a href="file/${board.fileurl }">${board.fileurl }</a>
						<a href="javascript:file_delete()">[÷�����ϻ���]</a>
					</div>
					</c:if>
					<input type="file" name="file1">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<a href="javascript:document.f.submit()">[�Խù�����]</a>
				<a href="list.shop?pageNum=${param.pageNum}">[������� ���ư���]</a>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>