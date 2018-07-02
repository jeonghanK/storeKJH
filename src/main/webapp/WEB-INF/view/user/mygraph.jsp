<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<!-- 뒷부분을 안지우면 4, 지우면 5 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>PIE graph</title>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
	<div id="canvas-holder" style="width: 70%">
		<canvas id="chart-area" width="200" height="200"></canvas>
	</div>
	<script type="text/javascript">
var randomColorFactor = function() {
	return Math.round(Math.random() * 255);
}
var randomColor = function(opacity) {
	return "rgba(" + randomColorFactor() + "," + randomColorFactor() + "," + randomColorFactor() + "," + (opacity || '.3') + ")";
};
var config = {
		type : 'pie', // 파이 그래프로 그리겠다
		data : {
			datasets : [{
				// ${map} : Map 객체(어드민 컨트롤러에서 지정)
				// m : Map.Entry(키, 값)쌍 객체
				// randomColor(1) : 0이면 투명.
				data : [<c:forEach items="${map}" var="m">"${m.value}",</c:forEach>],
				backgroundColor : [<c:forEach items="${map}" var="m">randomColor(1),</c:forEach>]
			}],
			labels:[<c:forEach items="${map}" var="m">"${m.key}",</c:forEach>]
		},
		options : {responsive : true} // 이미지 찌그러짐 방지
};
window.onload = function() { // 처음 윈도우가 올라가자 마자
	// 차트-에어리어 : canvas 차트를 그리고 뉴 차트로 차트-에어리어와 config의 옵션내용들로 어느 위치에 그리는지 설정.
	var ctx = document.getElementById("chart-area").getContext("2d"); 
	window.myPid = new Chart(ctx,config);
}
</script>
</body>
</html>