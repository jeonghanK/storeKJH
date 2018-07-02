<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>장바구니</title>
</head>
<body>
<h2>장바구니 물품</h2>
<table>
<tr><td colspan="2"><font color="green">장바구니 상품 목록</font></td></tr>
<tr><td>상품명</td><td>가격</td><td>수량</td><td>합계</td></tr>

<c:set var="tot" value="${0}" /><!-- 합계 초기값은 0으로 forEach문 바깥으로 하나 있어야한다. -->
<c:forEach items="${cart.itemList}" var="itemSet" varStatus="stat">
<tr><td>${itemSet.item.name}</td><td>${itemSet.item.price}</td>
<td>${itemSet.quantity}</td>
<td>${itemSet.quantity * itemSet.item.price}
<!-- index를 보냄. index는 순서. ArrayList는 순서를 유지하는 특징이있다. 여기의 index순서가 List의 순서와 같다. -->
<a href="cartDelete.shop?index=${stat.index}">Χ</a>
</td></tr>
<c:set var="tot" value="${tot + (itemSet.quantity*itemSet.item.price)}" />
</c:forEach>

<tr><td colspan="4" align="right"><font color="green">
총 구입금액 : ${tot}원</font></td></tr></table>
<br>${message}<br><a href="../item/list.shop">상품목록</a>
<a href="checkout.shop">주문하기</a>
</body>
</html>