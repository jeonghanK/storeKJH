<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품 정보 확인</title>
</head>
<body>
<h2>상품 정보 확인</h2>
<table>
<tr><td><img src="../picture/${item.pictureUrl}" width="200" height="250"></td></tr>
<td align="center">
<table>
<tr><td width="80">상품명</td><td width="160">${item.name}</td></tr>
<tr><td width="80">가격</td><td width="160">${item.price}</td></tr>
<tr><td width="80">상품개요</td><td width="160">${item.description}</td></tr>
<tr><td colspan="2">
<%-- 삭제하기 : id에 해당하는 상품 정보를 제거하기.
                             제거 성공시 list.shop으로
                             제거 실패시 confirm.shop
 --%>
<input type="button" value="삭제" onclick="location.href='delete.shop?id=${item.id}'">&nbsp;
<input type="button" value="상품목록" onclick="location.href='list.shop'">
</td></tr>
</table>
</td>
</table>
</body>
</html>