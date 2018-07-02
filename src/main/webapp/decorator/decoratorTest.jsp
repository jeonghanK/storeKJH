<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title><decorator:title /></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
table, th, td {
    border: 1px solid black;
}
.button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 16px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
}

.button5 {
    background-color: white;
    color: black;
    border: 2px solid #555555;
}

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
   max-height: 1000px;
}

.css3-tab input[id='tabOne']:checked ~ .css3-tab-nav label[for='tabOne']
{
   background: #b6ff00;
   color: #b6ff00;
   cursor: default;
}

.css3-tab input[id='tabOne']:checked ~ div.tab-one 
{
   display: block;
   border-top: solid 3px #b6ff00;
}

.css3-tab input[id='tabTwo']:checked ~ .css3-tab-nav label[for='tabTwo']
{
   background: #b6ff00;
   color: white;
   cursor: default;
}

.css3-tab input[id='tabTwo']:checked ~ div.tab-two 
{
   display: block;
   border-top: solid 3px red;
}

.css3-tab input[id='addNew']:checked ~ .css3-tab-nav label[for='addNew']
{
   background: #b6ff00;
   color: white;
   cursor: default;
}

.css3-tab input[id='addNew']:checked ~ div.add-new 
{
   display: block;
   border-top: solid 3px red;
}
</style>
<body class="w3-content w3-border-left w3-border-right" style="max-width: 1600px">
<!-- 헤더 부분 -->
  <header id="portfolio">
    <a href="#">
    	<img src="/w3images/avatar_g2.jpg" style="width:65px;" class="w3-circle w3-right w3-margin w3-hide-large w3-hover-opacity">
    </a>
    <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()">
    	<i class="fa fa-bars"></i>
    </span>
    <div class="w3-container">
    <h1><b>맨 이즈</b></h1>
    <div class="w3-section w3-bottombar w3-padding-16">
      <span class="w3-margin-right">Filter:</span> 
      <button class="w3-button w3-black">ALL</button>
      <button class="w3-button w3-white"><i class="fa fa-diamond w3-margin-right"></i>Design</button>
      <button class="w3-button w3-white w3-hide-small"><i class="fa fa-photo w3-margin-right"></i>Photos</button>
      <button class="w3-button w3-white w3-hide-small"><i class="fa fa-map-pin w3-margin-right"></i>Art</button>
    </div>
    </div>
  </header>
  
  
<h2><b>상품 상세보기</b></h2>
	<table width="1300" height="500" style="margin:50px">
	 <tr>
	 	 <td rowspan="6" width="800">사진</td>
	 	 <td colspan="2" width="200" height="80">상품명</td>
	 </tr>
	 <tr>
	 	 <td width="180">판매가</td>
		 <td>test</td>
	 </tr>
	 <tr>
	 	 <td width="180">Size</td>
		 <td>test</td>
	 </tr>
 	 <tr>
 	 	 <td width="180">Color</td>
		 <td>test</td>
	 </tr>
	 <tr>
 	 	 <td width="180">구매수량</td>
		 <td>test</td>
	 </tr>
	  <tr>
	 	 <td colspan="2" width="200" height="80">
	 	 	<button class="button button5">장바구니 추가</button>
	 	 	<button class="button button5">바로 구매</button>
	 	 </td>
	  </tr>
	</table>
 </body>
</html>
  
  

   <!-- 메뉴에 대한 설정 -->
   <div class='css3-tab'>
      <input type='radio' name='a' id='tabOne' tabindex="1"> 
      <input type='radio' name='a' id='tabTwo' tabindex="2" checked> 
      <input type='radio' name='a' id='addNew' tabindex="3">
		
	  <!-- 탭 항목 이름 설정하는 부분 -->
      	<div class="css3-tab-nav">
         	<label for='tabOne'>상품 설명</label> 
         	<label for='tabTwo'>후기</label> 
         	<label for='addNew'>상품/배송 문의</label>
      	</div>
		<!-- 상품 설명 눌렀을 때  -->
      <div class='css3-tab-content tab-one'>
         	<h1>modal!</h1>
      </div>
      
	  <!-- 후기 탭 눌렀을 때 후기 작성 리스트가 보여지는 곳 -->
   <div class='css3-tab-content tab-two'>
      <table width="1200" height="200" style="margin:0px">
      	<tr>
      		<td>
      		</td>
      	</tr>
      </table>
      	  <div class="w3-panel w3-border-top w3-border-bottom">
    		<p>작성자</p>
    		<p>작성일</p>
    		<p>평점</p>
    		<p>내용</p>
  		  </div>
  		  <div class="w3-panel w3-border-top w3-border-bottom">
    		<p>작성자</p>
    		<p>작성일</p>
    		<p>평점</p>
    		<p>내용</p>
  		  </div>
       		<button onclick="document.getElementById('id01').style.display='block'" 
       		class="w3-button" style="float: right;">후기작성</button>
    </div>

      <div class='css3-tab-content add-new'>
			<button onclick="document.getElementById('id01').style.display='block'" class="w3-button">글쓰기</button>
	  </div>
	  
<!-- modal 창 속성(후기 글 작성) -->
	<div id="id01" class="w3-modal">
  		<div class="w3-modal-content">
    		<div class="w3-container">
      			<span onclick="document.getElementById('id01').style.display='none'" 
      				class="w3-button w3-display-topright">&times;
      			</span>
      				<div class="w3-panel w3-border w3-margin" >
    					
      			<table width="70%" cellpadding="0" cellspacing="1" border="0">
     				 <tr>
      			      	<td align="center">제목</td>
      				  	<td><input name="title" size="50" maxlength="100"></td>
     				 </tr>
     				 <tr height="1"><td colspan="4"></td></tr>
    				 <tr>
      					<td align="center">작성자</td>
      					<td><input name="name" size="50" maxlength="50"></td>
     				 </tr>
      				 <tr height="1"><td colspan="4"></td></tr>
     				 <tr height="1"><td colspan="4"></td></tr>
     				 <tr>
      					<td align="center">내용</td>
      					<td><textarea name="memo" cols="50" rows="13"></textarea></td>
     				 </tr>
     				 <tr height="1"><td colspan="4"></td></tr>
     				 <tr height="1"><td colspan="4"></td></tr>
     				 <tr align="center">
      					<td colspan="2">
      						<input type=button value="등록">
       						<input type=button value="취소">
       					</td>
     				</tr>
    			</table>
    		 </div>
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

  
  
  <!-- Footer -->
  <footer class="w3-container w3-padding-32 w3-dark-grey">
  <div class="w3-row-padding">
    <div class="w3-third">
      <h3>FOOTER</h3>
      <p>Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>
      <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
    </div>
  
    <div class="w3-third">
      <h3>BLOG POSTS</h3>
      <ul class="w3-ul w3-hoverable">
        <li class="w3-padding-16">
          <span class="w3-large">Lorem</span><br>
          <span>Sed mattis nunc</span>
        </li>
        <li class="w3-padding-16">
          <span class="w3-large">Ipsum</span><br>
          <span>Praes tinci sed</span>
        </li> 
      </ul>
    </div>

    <div class="w3-third">
      <h3>POPULAR TAGS</h3>
      <p>
        <span class="w3-tag w3-black w3-margin-bottom">Travel</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">New York</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">London</span>
        <span class="w3-tag w3-grey w3-small w3-margin-bottom">IKEA</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">NORWAY</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">DIY</span>
        <span class="w3-tag w3-grey w3-small w3-margin-bottom">Ideas</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Baby</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Family</span>
        <span class="w3-tag w3-grey w3-small w3-margin-bottom">News</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Clothing</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Shopping</span>
        <span class="w3-tag w3-grey w3-small w3-margin-bottom">Sports</span> <span class="w3-tag w3-grey w3-small w3-margin-bottom">Games</span>
      </p>
    </div>

  </div>
  </footer>
  
  <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div>

<!-- End page content -->
</div>

<script>
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}
</script>

</body>
</html>
