<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<!-- �޺κ��� ������� 4, ����� 5 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�۾��� �м�</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.awesomeCloud-0.2.js"></script>

<style type="text/css">
.wordcloud {
	border: 1px solid #036;
	height: 4in;
	margin: 0.5in auto;
	padding: 0;
	page-break-after: always;
	page-break-inside: avoid;
	width: 4in;
}
</style>
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
</head>

<body>
	<header style="margin-top: 50px;"> </header>
	<div role="main">
		<div id="wordcloud1" class="wordcloud">
			<c:forEach items="${map}" var="m">
			<!-- ${m.key} : ȭ�鿡 ����� ����(�۾���) -->
				<span data-weight="${m.value*20}">${m.key}</span>
			</c:forEach>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$("#wordcloud1").awesomeCloud({
				"size" : {
					"grid" : 9,
					"factor" : 1
				},
				"options" : {
					"color" : "random-dark",
					"rotationRatio" : 0.35 // ���ڵ��� ��ġ���� �κ�
				},
				"font" : "'Times New Roman', Times, serif",
				"shape" : "circle"
			});
		});
	</script>
</body>
</html>