<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>마이페이지</title>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("minfo").style.display = "block";
		document.getElementById("oinfo").style.display = "none";
	}
	function disp_div(id) {
		document.getElementById("minfo").style.display = "none";
		document.getElementById("oinfo").style.display = "none";
		document.getElementById(id).style.display = "block";
	}
	function list_disp(id) {
		var disp = document.getElementById(id);
		if (disp.style.display == 'block') {
			disp.style.display = 'none'; // 보여주고 있다면 다시 눌르면 안보여줌.
		} else {
			disp.style.display = 'block'; // 안보여주고 있다면 보여줌.
		}
	}
	function graph_open(url) {
		var op = "width=700,height=500,scrollbars=yes,left=50,top=150";
		window.open(url + ".shop?id=${user.userId}", "graph", op);
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td><a href="javascript:disp_div('minfo')">회원 정보 보기</a></td>
			<td><a href="javascript:disp_div('oinfo')">주문 정보 보기</a></td>
		</tr>
	</table>
	<div id="oinfo" style="display: block; width: 100%;">
		<table border="1" width="100%">
			<tr>
				<td colspan="3">주문 목록</td>
			</tr>
			<tr>
				<td>주문번호</td>
				<td>주문일자</td>
				<td>주문금액</td>
			</tr>
			<c:forEach items="${salelist}" var="sale" varStatus="stat">
				<tr>
					<td align="center"><a
						href="javascript:list_disp('saleLine${stat.index}')">${sale.saleId}</a></td>
					<td><fmt:formatDate value="${sale.updateTime}"
							pattern="yyyy-MM-dd" /></td>
					<td>${sale.amount}</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<div id="saleLine${stat.index}" style="display: none;">
							<table border="1" width="90%">
								<tr>
									<th width="25%">상품명</th>
									<th width="25%">상품가격</th>
									<th width="25%">수량</th>
									<th width="25%">가격합계</th>
								</tr>
								<c:forEach items="${sale.saleItemList}" var="saleItem">
									<tr>
										<td>${saleItem.item.name}</td>
										<td>${saleItem.item.price}</td>
										<td>${saleItem.quantity}</td>
										<td>${saleItem.quantity * saleItem.item.price}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="minfo" style="display: block; width: 100%">
		<table border="1" width="100%">
			<tr>
				<td>아이디</td>
				<td>${user.userId}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>${user.postcode}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${user.phoneNo}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${user.address}</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><fmt:formatDate value="${user.birthDay}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
		</table>
	</div>
	<br>
	<a href="update.shop?id=${user.userId}">[회원정보수정]</a>&nbsp;
	<c:if test="${loginUser.userId != 'admin'}">
		<a href="delete.shop?id=${user.userId}">[회원탈퇴]</a>&nbsp;
	</c:if>
	<a href="logout.shop">[로그아웃]</a>&nbsp;
	<c:if test="${loginUser.userId == 'admin'}">
		<a href="../admin/admin.shop">[회원목록보기]</a>&nbsp;
	</c:if>

	<c:if test="${loginUser.userId != 'admin'}">
		<a href="javascript:graph_open('mygraph')">[구매상품그래프보기(파이)]</a>&nbsp;
	</c:if>
</body>
</html>