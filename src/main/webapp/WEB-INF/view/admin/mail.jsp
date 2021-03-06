<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>메일 보내기</title>
<script type="text/javascript" src="//cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
</head>
<body>
<h2>메일 보내기</h2>
<form name="mailform" method="post" action="mail.shop" enctype="multipart/form-data">
네이버 메일 주소 : <input type="text" name="naverid" value="">
네이버 메일 비밀번호 : <input type="password" name="naverpass" value="">
<table border="1" cellpadding="0" cellspacing="0" width="100%">
<tr><td>보내는 사람</td><td>${sessionScope.loginUser.email}</td></tr>
<tr><td>받는 사람</td><td>
<!-- lt, gt : 열고 닫는 꺽은 괄호. value안에 들어가는 값은 한줄로 작성하여야 한다. -->
<input type="text" name="recipient" size="100" value='<c:forEach items="${userList}" var="user">${user.userName} &lt;${user.email}&gt;,</c:forEach>'></td></tr>
<tr><td>제목</td>
    <td><input type="text" name="title" size="100"></td></tr>
<tr><td>메세지 형식</td>
    <td><select name="mtype">
<option value="text/html;charset=euc-kr">HTML</option>
<option value="text/plain;charset=euc-kr">TEXT</option>
</select></td></tr>
<tr><td>첨부파일1</td><td><input type="file" name="file1"></td></tr>
<tr><td>첨부파일2</td><td><input type="file" name="file1"></td></tr>
<tr><td colspan="2">
<textarea name="contents" cols="120" rows="10"></textarea>
<script type="text/javascript">CKEDITOR.replace('contents');</script>
</td></tr>
<tr><td colspan="2" align="center">
<input type="submit" value="메일보내기"></td></tr>
</table>
</form>
</body>
</html>