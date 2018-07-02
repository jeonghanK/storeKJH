<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%><!-- include : 포함하다. 해당 jsp의 내용을 그대로 가져온다는 것. -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품목록</title>
</head>
<body>
	<a href="create.shop">상품등록</a>
	<a href="../cart/cartView.shop" style="float: right;">장바구니</a>
	<table border="1" cellspacing="0" cellpadding="0">
		<tr>
			<th align="center" width="80">상품ID</th>
			<th align="center" width="320">상품명</th>
			<th align="center" width="100">가격</th>
			<th align="center" width="80">수정</th>
			<th align="center" width="80">삭제</th>
		</tr>
		<c:forEach items="${itemList}" var="item"><!-- 아이템컨트롤러에서 mav에 추가된 객체들 -->
			<tr>
				<td align="center">${item.id}</td>
				<td align="left"><a href="detail.shop?id=${item.id}">${item.name}</a></td>
				<!-- W원 표시를 없애기 위해 Sybol을 공백으로 처리 -->
				<td align="right"><fmt:formatNumber type="CURRENCY" currencySymbol="" value="${item.price}" />원</td>
				<td align="center"><a href="edit.shop?id=${item.id}">수정</a></td>
				<td align="center"><a href="confirm.shop?id=${item.id}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>