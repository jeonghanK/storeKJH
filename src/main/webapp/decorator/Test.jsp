<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<!doctype html>
<html>
<head>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>
<meta charset="UTF-8">
<style>
.css3-tab {
   list-style: none;
   margin: 0 auto 40px;
   padding: 38px 0 0 0;
   position: relative;
   width: 90%;
}

.css3-tab input[type='radio'] {
   display: none;
}

.css3-tab .css3-tab-nav {
   display: table;
   table-layout: fixed;
   width: 100%;
}

.css3-tab .css3-tab-nav label {
   display: table-cell;
   background-color: #666666;
   color: #FFFFFF;
   padding: 15px;
   text-align: center;
   transition: all .3s ease 0s;
}

.css3-tab .css3-tab-nav label:hover {
   cursor: pointer;
   background: white;
   color: #666666;
   transition: all .3s ease 0s;
}

@media ( max-width : 692px) {
   .css3-tab .css3-tab-nav {
      display: block;
      margin: 0 0 20px;
   }
   .css3-tab .css3-tab-nav label {
      display: block;
      box-sizing: border-box;
      width: 100%;
      padding: 20px;
   }
}

.css3-tab .css3-tab-content {
   overflow: hidden;
   padding: 25px;
   display: none;
   background: #FFF;
   clear: left;
   box-sizing: border-box;
}

.css3-tab input[id='tabOne']:checked ~ .css3-tab-nav label[for='tabOne']
   {
   background: #b6ff00;
   color: #666666;
   cursor: default;
}

.css3-tab input[id='tabOne']:checked ~ div.tab-one {
   display: block;
   border-top: solid 3px #b6ff00;
}

.css3-tab input[id='tabTwo']:checked ~ .css3-tab-nav label[for='tabTwo']
   {
   background: red;
   color: white;
   cursor: default;
}

.css3-tab input[id='tabTwo']:checked ~ div.tab-two {
   display: block;
   border-top: solid 3px red;
}

.css3-tab input[id='addNew']:checked ~ .css3-tab-nav label[for='addNew']
   {
   background: red;
   color: white;
   cursor: default;
}

.css3-tab input[id='addNew']:checked ~ div.add-new {
   display: block;
   border-top: solid 3px red;
}
</style>
</head>
<body>
   <!-- 메뉴에 대한 설정 -->
   <div class='css3-tab'>
      <input type='radio' name='a' id='tabOne' tabindex="1"> 
      <input type='radio' name='a' id='tabTwo' tabindex="2" checked> 
      <input type='radio' name='a' id='addNew' tabindex="3">
		
	  <!-- 탭 항목 이름 설정하는 부분 -->
      	<div class="css3-tab-nav">
         	<label for='tabOne'>무엇이든 물어보세요</label> 
         	<label for='tabTwo'>주문내역 조회</label> 
         	<label for='addNew'>미정</label>
      	</div>

      <div class='css3-tab-content tab-one'>
         	<h1>modal!</h1>
      </div>

      <div class='css3-tab-content tab-two'>
       		<button onclick="document.getElementById('id01').style.display='block'" class="w3-button">글쓰기</button>
      </div>

      <div class='css3-tab-content add-new'>
			<button onclick="document.getElementById('id01').style.display='block'" class="w3-button">글쓰기</button>
	  </div>
<!-- modal 창 속성 -->
	<div id="id01" class="w3-modal">
  		<div class="w3-modal-content">
    		<div class="w3-container">
      		<span onclick="document.getElementById('id01').style.display='none'" 
      		class="w3-button w3-display-topright">&times;
      		</span>
      			<p>테스트</p>
      			<p>중입니다.</p>
    		</div>
  		</div>
	</div>
	<div id="id02" class="w3-modal">
  		<div class="w3-modal-content">
    		<div class="w3-container">
      		<span onclick="document.getElementById('id02').style.display='none'" 
      		class="w3-button w3-display-topright">&times;
      		</span>
      			<p>테스트</p>
      			<p>중입니다.</p>
    		</div>
  		</div>
	</div>		
      </div>
</body>
</html>






