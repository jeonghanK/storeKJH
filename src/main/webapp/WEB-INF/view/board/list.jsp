<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ��� ���</title>
<script type="text/javascript">
	function list(pageNum) {
		var searchType = document.searchform.searchType.value;
		if (searchType == null || searchType.length == 0) {
			document.searchform.searchContent.value = "";
			document.searchform.pageNum.value = "1";
			location.href = "list.shop?pageNum=" + pageNum;
		} else {
			document.searchform.pageNum.value = pageNum;
			document.searchform.submit();
			return true;
		}
		return false;
	}
</script>
</head>
<body>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
  <tr><td colspan="5" align="center">
  <form action="list.shop" method="post" name="searchform" onsubmit="return list(1)">
  <input type="hidden" name="pageNum" value="1">
  
<select name="searchType" id="searchType">
  <option value="">�����ϼ���</option>
  <option value="subject">����</option>
  <option value="name">�۾���</option>
  <option value="content">����</option>
</select>&nbsp;

<script type="text/javascript">
if('${param.searchType}' != '') {
	document.getElementById("searchType").value = '${param.searchType}'
}
</script>
<input type="text" name="searchContent" value="${param.searchContent }">
<input type="submit" value="�˻�">
</form>
</td></tr>
		<c:if test="${listcount > 0}">
			<tr align="center" valign="middle">
				<td colspan="4">Spring �Խ���</td>
				<td>�� ���� : ${listcount}</td>
			</tr>
			
			<tr align="center" valign="middle" bordercolor="#212121">
				<th width="8%" height="26">��ȣ</th>
				<th width="50%" height="26">����</th>
				<th width="14%" height="26">�۾���</th>
				<th width="17%" height="26">��¥</th>
				<th width="11%" height="26">��ȸ��</th>
			</tr>
			
			<c:forEach var="board" items="${boardlist}">
				<tr align="center" valign="middle" bordercolor="#333333"
					onmouseover="this.style.backgroundColor='#5CD1E5'"
					onmouseout="this.style.backgroundColor=''">

					<td height="23">${boardcnt}</td>
					<c:set var="boardcnt" value="${boardcnt - 1}" />
					<td align="left">
					<c:if test="${not empty board.fileurl}">
						<a href="../file/${board.fileurl}">@</a>
					</c:if> 
					
					<c:if test="${empty board.fileurl}">&nbsp;&nbsp;&nbsp;</c:if> 
					<c:forEach begin="1" end="${board.reflevel}">&nbsp;&nbsp;&nbsp;</c:forEach>
					<c:if test="${board.reflevel > 0}">��</c:if> 
					
					<!-- ������ pageNum�� ��Ʈ�ѷ����� addObject�� �Ӽ����� ���⶧���� param�� ������ �ȵȴ�. -->
					<a href="detail.shop?num=${board.num}&pageNum=${pageNum}">${board.subject}</a>
					</td>
					<td align="left">${board.name}</td>
					<td align="center">${board.regdate}</td>
					<td align="left">${board.readcnt}</td>
				</tr>
			</c:forEach>
			
			<tr align="center" height="26">
				<td colspan="5">
				<c:if test="${pageNum > 1}">
					<a href="javascript:list(${pageNum -1})">[����]</a>
				</c:if>&nbsp; 
					
				<c:if test="${pageNum <= 1}">[����]</c:if>&nbsp; 
				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == pageNum}">[${a}]</c:if>
					<c:if test="${a != pageNum}">
						<a href="javascript:list(${a})">[${a}]</a>
					</c:if>
				</c:forEach> 
					
				<c:if test="${pageNum < maxpage}">
					<a href="javascript:list(${pageNum + 1})">[����]</a>
				</c:if>&nbsp; <c:if test="${pageNum >= maxpage}">[����]</c:if>&nbsp;
				</td>
			</tr>
		</c:if>
		
		<c:if test="${listcount == 0}">
		<tr><td colspan="5">��ϵ� �Խù��� �����ϴ�.</td></tr>
		</c:if>
		<tr><td align="right" colspan="5">
		<a href="write.shop">[�۾���]</a>
		</td></tr>
</table>
</body>
</html>