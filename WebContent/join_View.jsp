<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
  
body {
  background-color: #363E4A;
  font-family: "Avenir Next", "Avenir", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 16px;
  color: #ffffff;
  line-height: 1.5;
}

h1 {
  font-family: "Texta", "Avenir Next", "Avenir", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 4px;
  text-align: center;
}

h2 {
  font-weight: 400;
  font-size: 24px;
  color: rgba(255, 255, 255, 0.4);
}

.container {
  max-width: 320px;
  margin: 0 auto;
  padding: 20px 16px 40px 16px;
  transform: translateZ(0);
  text-align: center;
}

.form-footer {
  margin-top: 2em;
  display:inline-block;
  margin-top:-8px;
  margin-right: 18px;
  
}

.ui-input {
  position: relative;
  padding: 0;
  border: 0;
}

.ui-input input {
  font-family: "Avenir Next", "Avenir", "Helvetica Neue", Helvetica, Arial, sans-serif;
  border: 0;
  background: none;
  padding: 16px 0 16px 0;
  font-size: 24px;
  outline: 0;
  width: 100%;
  tap-highlight-color: rgba(0, 0, 0, 0);
  touch-callout: none;
}

.ui-input input + label {
  position: relative;
  display: block;
  padding: 8px 0 8px 0;
  text-transform: uppercase;
  font-size: 14px;
  letter-spacing: .0875em;
  font-weight: 500;
  text-align: left;
  
}
.ui-input input + label::before, .ui-input input + label::after {
  position: absolute;
  top: 0;
  left: 0;
  content: "";
  width: 100%;
  height: 1px;
}
.ui-input input + label::before {
  background-color: rgba(255, 255, 255, 0.2);
}
.ui-input input + label::after {
  transform: scaleX(0);
  transform-origin: left;
  transition: transform 300ms cubic-bezier(0.215, 0.61, 0.355, 1);
  background-color: #6EB1FF;
  height: 2px;
}
.ui-input input + label span {
  position: relative;
  color: rgba(255, 255, 255, 0.2);
  transition: color 300ms cubic-bezier(0.215, 0.61, 0.355, 1);
}
.ui-input input + label span::after {
  content: attr(data-text);
  position: absolute;
  overflow: hidden;
  left: 0;
  transform: scaleX(1);
  white-space: nowrap;
  color: #fff;
  background-image: linear-gradient(to right, #4A90E2 50%, rgba(255, 255, 255, 0) 0%);
  background-position: 100% 50%;
  background-size: 200%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  backface-visibility: hidden;
  perspective: 1000;
  transform: translateZ(0);
  transition: background-position 300ms cubic-bezier(0.215, 0.61, 0.355, 1);
}

.ui-input input:focus + label::after,
.ui-input input.error + label::after,
.ui-input input:invalid + label::after,
.ui-input input.filled + label::after {
  transform: scaleX(1);
  transform-origin: left;
}
.ui-input input:focus + label span::after,
.ui-input input.error + label span::after,
.ui-input input:invalid + label span::after,
.ui-input input.filled + label span::after {
  background-image: linear-gradient(to right, white 50%, rgba(255, 255, 255, 0) 0%);
  background-position: 0% 50%;
}

.ui-input input.filled {
  color: #ffffff;
}
.ui-input input.filled + label::after {
  background-color: #ffffff;
}
.ui-input input.filled + label span::after {
  background-image: linear-gradient(to right, #ffffff 50%, rgba(255, 255, 255, 0) 0%);
  background-position: 0% 50%;
}

.ui-input input:focus {
  color: #6EB1FF;
}
.ui-input input:focus + label::after {
  background-color: #6EB1FF;
}
.ui-input input:focus + label span::after {
  background-image: linear-gradient(to right, #6EB1FF 50%, rgba(255, 255, 255, 0) 0%);
  background-position: 0% 50%;
}

.ui-input input.error,
.ui-input input:invalid {
  color: #E66161;
}
.ui-input input.error + label::after,
.ui-input input:invalid + label::after {
  background-color: #E66161;
}
.ui-input input.error + label span::after,
.ui-input input:invalid + label span::after {
  background-image: linear-gradient(to right, #E66161 50%, rgba(255, 255, 255, 0) 0%);
  background-position: 0% 50%;
}

.btn {
  border: 0;
  background-color: #50617A;
  padding: 18px 30px;
  font-size: 14px;
  line-height: 1.5;
  text-transform: uppercase;
  letter-spacing: .0875em;
  font-weight: 500;
  color: #ffffff;
  font-family: "Avenir Next", "Avenir", "Helvetica Neue", Helvetica, Arial, sans-serif;
  border-radius: 100px;
  outline: 0;
  transition: background-color 300ms cubic-bezier(0.215, 0.61, 0.355, 1), color 300ms cubic-bezier(0.215, 0.61, 0.355, 1);
}

.btn:focus, .btn:active,
.btn:hover {
  background-color: #6EB1FF;
  color: white;
}

.__first, .__second, .__third, .__fourth .__fifth .__sixth .__seventh .__eigth{
  animation-name: fadeIn;
  animation-duration: 180ms;
  animation-fill-mode: both;
  animation-iteration-count: 1;
}

.__first {
  animation-delay: 0;
}

.__second {
  animation-delay: 80ms;
}

.__third {
  animation-delay: 180ms;
}

.__fourth {
  animation-delay: 360ms;
  margin-top:37px;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translate3d(0, -25%, 0);
  }
  to {
    opacity: 1;
    transform: translate3d(0, 0, 0);
  }
}


.container2{
  display: block;
  position: relative;
  height: auto;
  width: 500px;
  padding: 20px;
  padding-left: 0px;
}

.container2 ul{
  list-style: none;
  margin: 0;
  padding: 0;
	overflow: auto;
}

ul li{
  color: #AAAAAA;
  display: block;
  position: relative;
  float: left;
  height: 100px;
  border-bottom: 1px solid #333;
  padding-right: 55px;
}

ul li input[type=radio]{
  position: absolute;
  visibility: hidden;
}

ul li label{
  display: block;
  position: relative;
  font-weight: 300;
  font-size: 1.35em;
  padding: 25px 25px 25px 80px;
  height: 30px;
  z-index: 9;
  cursor: pointer;
  -webkit-transition: all 0.25s linear;
}

ul li:hover label{
	color: #FFFFFF;
}

ul li .check{
  display: block;
  position: absolute;
  border: 5px solid #AAAAAA;
  border-radius: 100%;
  height: 25px;
  width: 25px;
  top: 30px;
	z-index: 5;
	transition: border .25s linear;
	-webkit-transition: border .25s linear;
}

ul li:hover .check {
  border: 5px solid #FFFFFF;
}

ul li .check::before {
  display: block;
  position: absolute;
	content: '';
  border-radius: 100%;
  height: 15px;
  width: 15px;
  top: 5px;
	left: 5px;
  margin: auto;
	transition: background 0.25s linear;
	-webkit-transition: background 0.25s linear;
}

input[type=radio]:checked ~ .check {
  border: 5px solid #4A90E2;
}

input[type=radio]:checked ~ .check::before{
  background: #4A90E2;
}

input[type=radio]:checked ~ label{
  color: #4A90E2;
}

.signature {
	margin: 10px auto;
	padding: 10px 0;
	width: 100%;
}

.signature p{
	text-align: center;
	font-family: Helvetica, Arial, Sans-Serif;
	font-size: 0.85em;
	color: #AAAAAA;
}

.signature .much-heart{
	display: inline-block;
	position: relative;
	margin: 0 4px;
	height: 10px;
	width: 10px;
	background: #AC1D3F;
	border-radius: 4px;
	-ms-transform: rotate(45deg);
    -webkit-transform: rotate(45deg);
    transform: rotate(45deg);
}

.signature .much-heart::before, 
.signature .much-heart::after {
	  display: block;
  content: '';
  position: absolute;
  margin: auto;
  height: 10px;
  width: 10px;
  border-radius: 5px;
  background: #AC1D3F;
  top: -4px;
}

.signature .much-heart::after {
	bottom: 0;
	top: auto;
	left: -4px;
}

.signature a {
	color: #AAAAAA;
	text-decoration: none;
	font-weight: bold;
}


.alert {
	box-sizing: border-box;
	background-color: #4A90E2;
	width: 100%;
	position: relative; 
	top: 0;
	left: 0;
	z-index: 300;
	padding: 20px 40px;
	color: #333;
}

.alert h2 {
	font-size: 22px;
	color: #232323;
	margin-top: 0;
}

.alert p {
	line-height: 1.6em;
	font-size:18px;
}

.alert a {
	color: #232323;
	font-weight: bold;
}

</style>
<script type="text/javascript">
	function back(){
		history.back();
	}
	
	var $input = $('.form-fieldset > input');

	$input.blur(function (e) {
	  $(this).toggleClass('filled', !!$(this).val());
	});
</script>
</head>

<body>

<div class = "header">
	<h1 class = "Logo">
		<a href = "./main.me"><img src = "source/images/SKOV2.png" width = "300" height = "100"/></a>
	</h1>
</div>




<form action="./memberJoinAction.me" method="post" name = "joinForm">

	<div class="container" >
	
	<fieldset class="form-fieldset ui-input __first">
   <input type="text" name="member_id" id="new-id" required="required" />
   <label for="new-id">
    <span data-text="아이디">아이디</span>
    </label>
    </fieldset>
    
    <fieldset class="form-fieldset ui-input __second">
   <input type="password" name="member_pw" id="new-password" required="required" min="5"/>
   <label for="new-password">
   <span data-text="비밀번호">비밀번호</span>
   </label>
   </fieldset>
   
   <fieldset class="form-fieldset ui-input __third">
   <input type="text" name="member_name" id="new-name" />
   <label for="new-name">
   <span data-text="이름">이름</span>
   </label>
   </fieldset>
	
	<fieldset class="form-fieldset ui-input __fourth"> 
   <input type="text" name="member_age" id="new-age"/>
   <label for="new-age">
   <span data-text="나이">나이</span>
   </label>
   </fieldset>
   
   
   <div class="container2">
   <fieldset class="form-fieldset ui-input __fifth"> 
   <ul>
  		<li >
   <input type="radio" name="member_gender" value="남자"  id="f-option"/><label for="f-option" style="MARGIN-TOP: 4px; MARGIN-LEFT: 4px;">남자</label>
   <div class="check"></div>
   		</li>
   		
   		<li>
   <input type="radio" name="member_gender" value="여자" id="t-option" /><label for="t-option" style="MARGIN-TOP: 4px; MARGIN-LEFT: 4px;">여자</label>
   <div class="check"><div class="inside"></div></div>
  		</li>
  	</ul>
  	</fieldset>
  	</div>
   
   <fieldset class="form-fieldset ui-input __sixth"> 
   <input type="email" name="member_email" id="new-email required="required"/>
    <label for="new-email">
   <span data-text="이메일">이메일</span>
   </label>
   </fieldset>
   
    <fieldset class="form-fieldset ui-input __seventh"> 
   <input type="text" name="member_addr" id="new-addr"/>
    <label for="new-addr">
   <span data-text="주소">주소</span>
   </label>
   </fieldset>
   
   <div class="container2">
   <fieldset class="form-fieldset ui-input .__eigth"> 
   
   <ul>
  		<li>
   <input type="radio" name="member_skin" value="지성" id="a-option" /><label for="a-option">지성</label>
   <div class="check"></div>
		</li>
		
		<li>
   <input type="radio" name="member_skin" value="복합성" id="b-option" /><label for="b-option">복합성</label>
   <div class="check"></div>
		</li>
		
		<li>
   <input type="radio" name="member_skin" value="건성"  id="c-option"/><label for="c-option">건성</label>
   <div class="check"></div>
		</li>
		
		<li>
   <input type="radio" name="member_skin" value="트러블"  id="d-option"/><label for="d-option">여드름/아토피</label>
   <div class="check"></div>
		</li>
		
	</ul>
	</fieldset>
	</div>
	
	
	<div style="display:inline-block;">
		<div class="form-footer" >
  <button class="btn"> <a href = "javascript:joinForm.submit()" style="text-decoration:none; color:#FFFFFF;">회원가입</a></button>
  		</div>
  	
  		<div class="form-footer" >
   <button class="btn"> <a href = "javascript:joinForm.reset()" style="text-decoration:none; color:#FFFFFF;">다시작성</a></button>
 		</div>
 		
 		</div>

</div>
</form>


</body>
</html>