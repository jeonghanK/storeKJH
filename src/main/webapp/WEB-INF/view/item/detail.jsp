<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품상세보기</title>
</head>
<body>
	<h2>상품 상세 보기</h2>
	<table>
		<tr>
			<td><img src="../picture/${item.pictureUrl}" width="200" height="250"></td>
			<td align="center">
				<table>
					<tr>
						<td width="80">상품명</td>
						<td width="160">${item.name}</td>
					</tr>
					<tr>
						<td width="80">가격</td>
						<td width="160">${item.price}</td>
					</tr>
					<tr>
						<td width="80">상품개요</td>
						<td width="160">${item.description}</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<form action="../cart/cartAdd.shop">
								<input type="hidden" name="id" value="${item.id}">
								<table>
									<tr>
										<td><select name="quantity">
												<c:forEach begin="1" end="10" var="i">
													<option>${i}</option>
												</c:forEach>
										</select></td>
										<td><input type="submit" value="장바구니"><!-- 장바구니 클릭시 카트컨트롤러의 cartAdd 호출됨. -->
										<input type="button" value="상품목록" onclick="location.href='list.shop'">
										</td></tr>
								</table>
							</form>
				</table>
		</tr>
	</table>
</body>
</html>