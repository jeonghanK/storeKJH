<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>주문 전 상품 목록 보기</title>
<style type="text/css">
table {
	width: 90%;
	border-collapse: collapse;
}

th, td {
	border: 3px solid #bcbcbc;
	text-align: center;
	padding: 8px;
}

th {
	background-color: #4caf50;
	color: white;
	text-align: center;
}

td {
	background-color: #f2f2f2;
}

td.title {
	background-color: #e2e2e2;
	color: blue;
}
</style>
</head>
<body>
<h2>배송지 정보</h2>
<table>
<tr><td width="30%" class="title">구매자ID</td>
<td width="70%">${loginUser.userId}</td></tr>
<tr><td width="30%" class="title">이름</td>
<td width="70%">${loginUser.userName}</td></tr>
<tr><td width="30%" class="title">우편번호</td>
<td width="70%">${loginUser.postcode}</td></tr>
<tr><td width="30%" class="title">주소</td>
<td width="70%">${loginUser.address}</td></tr>
<tr><td width="30%" class="title">전화번호</td>
<td width="70%">${loginUser.phoneNo}</td></tr>
<tr><td width="30%" class="title">이메일</td>
<td width="70%">${loginUser.email}</td></tr>
</table><br><br>
<h2>장바구니 목록</h2>
<table>
<tr><th>상품명</th><th>상품가격</th><th>수량</th><th>상품합계</th></tr>
<!-- 내가 골라 담은 물건들을 CART로 저장해놨음. 그것들의 리스트를 가져와서 itemSet이라는 변수로 설정 -->
<!-- 풀네임은 sessionScope.CART.itemList -->
<c:forEach items="${CART.itemList}" var="itemSet">
<tr><td>${itemSet.item.name}</td><td>${itemSet.item.price} 원</td>
    <td>${itemSet.quantity}개</td>
    <td>${itemSet.quantity * itemSet.item.price}</td>
</tr></c:forEach>
<tr><td colspan="4" style="text-align:right;">총 금액 :
<fmt:formatNumber value="${CART.totalAmount}" pattern="#,###" />원<!-- 내가 고른 물건들의 총 합계 -->
</td></tr>
<tr><td colspan="4">
<a href="end.shop">확정</a>&nbsp;
<a href="../item/list.shop">상품목록</a>
</td></tr>
</table>
</body>
</html>