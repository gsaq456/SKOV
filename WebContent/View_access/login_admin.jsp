<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="source/CSS/style.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript">
function showPopup() { 
	window.open("./memberListAction.me", "회원 목록보기", "width=700, height=600, left=100, top=50"); 
}
</script>

</head>
<body>
	<div class = "login_form">

	<form action="./memberLogoutAction.me" name = "logoutForm">
			<div class="card">
		
			<img  src = "source/images/user.png" alt="John"  style="width:200px; height: 200px; margin-top: -65px;">
			<h1 style="margin-top: 55px; margin-right: -20px; color: black;"> <img  src = "source/images/1.png" style="position:absolute; width: 45px; height:39px; margin-top: -12px; margin-left: -42px;"> 관리자님 환영합니다.</h1>
			
		
			
		<div class="btn" style = "    margin-top: 17px; margin-right: 13px; margin-left: -3px;  width: 300px;">
			<p><a class="profile_btn" href  = "javascript:logoutForm.submit() "style = " color: white;">logout</a></p>
			<p><a class="profile_btn" href = "#" onclick = "showPopup();" style = " color: white;">memberlist</a></p>
		
		</div>
	</div>

</form>
</div>

</body>

</html>