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
body,h2,h3,h4,h5,h6 {
	font-family:Arial, Helvetica, sans-serif;
	margin:0 auto;
	font-family: "Raleway", sans-serif;
	text-align:center;
}

h1
{
	text-align:left;
}
table, th, td {
    width: 1200px;
    
}
table {
	font-family:Arial, Helvetica, sans-serif;
	color:#666;
	font-size:18px;
	background:#eaebec;
	margin:auto;
	border:#ccc 1px solid;
	border-radius:3px;
}
table tr{
	text-align: center;
	padding-left:20px;
}
table tr td:first-child{
	text-align: left;
	padding-left:20px;
	border-left: 0;
}
table tr td {
	padding:12px;
	border-top: 1px solid #ffffff;
	border-bottom:1px solid #e0e0e0;
	border-left: 1px solid #e0e0e0;
	
	background: #fafafa;
	background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));
	background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);
}
.w3-btn {width:150px;}
</style>
<body class="w3-content w3-border-left w3-border-right" style="max-width: 1600px">

<!-- ��� �κ� -->
  <header id="portfolio">
    <a href="#">
    </a>
    <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()">
    	<i class="fa fa-bars"></i>
    </span>
    <div class="w3-container">
    <h1><b>�� ����</b></h1>
    <div class="w3-section w3-bottombar w3-padding-16">
      <span class="w3-margin-right">Filter:</span> 
      <button class="w3-button w3-black">ALL</button>
      <button class="w3-button w3-white"><i class="fa fa-diamond w3-margin-right"></i>Design</button>
      <button class="w3-button w3-white w3-hide-small"><i class="fa fa-photo w3-margin-right"></i>Photos</button>
      <button class="w3-button w3-white w3-hide-small"><i class="fa fa-map-pin w3-margin-right"></i>Art</button>
    </div>
    </div>
  </header>
  
	<div class="w3-container">
  	 <h2>My page</h2>
  	 <div class="w3-center"><br>
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
        <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
      </div>
  	 	
		<table>
			<tr class='even'>
				<td>
					<button onclick="document.getElementById('id01').style.display='block'"
					class="w3-button w3-grey w3-round-large" style="float:right;">����</button>
					<button onclick="document.getElementById('id02').style.display='block'"
					class="w3-button w3-grey w3-round-large" style="float:right;">Ż��</button>
				</td>
			</tr>
			<tr>
				<td>�̸�</td>
			</tr>
			<tr class='even'>
				<td>��ȭ ��ȣ</td>
			</tr>
			<tr>
				<td>�ּ�</td>
			</tr>
			<tr class='even'>
				<td>e-mail</td>
			</tr>
		</table>
		</div>
		
      <div id="id01" class="w3-modal">
    	<div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
      	<div class="w3-center"><br>
        	<span onclick="document.getElementById('id01').style.display='none'" 
        	class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
        	<form class="w3-container" action="/action_page.php">
        		<div class="w3-section">
          		 <label><b>���̵�</b></label>
          			<input class="w3-input w3-border w3-margin-bottom" type="text" 
          			placeholder="Enter Username" name="usrname" required>
          		 <label><b>�н�����</b></label>
          			<input class="w3-input w3-border" type="password" placeholder="Enter Password" name="psw" required>
          		 <label><b>�н����� Ȯ��</b></label>
          			<input class="w3-input w3-border w3-margin-bottom" type="text" 
          			placeholder="Enter Username" name="usrname" required>
          		 <label><b>email</b></label>
          			<input class="w3-input w3-border" type="password" placeholder="Enter Password" name="psw" required>
          		 <label><b>�̸�</b></label>
          			<input class="w3-input w3-border w3-margin-bottom" type="text" 
          			placeholder="Enter Username" name="usrname" required>
          		 <label><b>�ּ�</b></label>
          			<input class="w3-input w3-border" type="password" placeholder="Enter Password" name="psw" required>
          		 <label><b>��ȭ��ȣ</b></label>
          			<input class="w3-input w3-border" type="password" placeholder="Enter Password" name="psw" required>
          		 <label><b>e-mail</b></label>
          			<input class="w3-input w3-border" type="password" placeholder="Enter Password" name="psw" required>
          			<button onclick="document.getElementById('id01').style.display='block'"
						class="w3-button w3-grey w3-round-large">���</button>
		  			<button onclick="document.getElementById('id02').style.display='block'"
						class="w3-button w3-grey w3-round-large">���</button>
        		 </div>
      		 </form>
      	    </div>
      	  </div>
        </div>
       <div id="id02" class="w3-modal">
    	 <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
      	  <div class="w3-center"><br>
        	<span onclick="document.getElementById('id02').style.display='none'" 
        	class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
        	<form class="w3-container" action="/action_page.php">
        <div class="w3-section">
          <label><b>������ Ż�� �Ͻðڽ��ϱ�?</b></label>
          <button onclick="document.getElementById('id01').style.display='block'"
					class="w3-button w3-grey w3-round-large">��</button>
		  <button onclick="document.getElementById('id02').style.display='block'"
					class="w3-button w3-grey w3-round-large">�ƴϿ�</button>
        </div>
      </form>
      	</div>
      	</div>
      </div>
      
  <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a>
  </div>
</body>
</html>








