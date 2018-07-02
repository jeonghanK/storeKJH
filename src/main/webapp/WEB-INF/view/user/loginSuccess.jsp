<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인 성공</title>
</head>
<body>
	<h2>환영합니다. ${loginUser.userName}님</h2>
	<a href="mypage.shop?id=${loginUser.userId}">MY PAGEEEEE</a>
	<a href="../item/list.shop">상품목록보기</a>
	<a href="logout.shop">로갸웃</a>
</body>
</html>