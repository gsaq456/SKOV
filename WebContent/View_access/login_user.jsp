<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.MemberBean" %>

<%

	
	MemberBean member_info = (MemberBean)session.getAttribute("member_info");
    
%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="source/CSS/style.css">

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript">
	function showPopup() { 
		window.open("./memberViewAction.me?m_id=<%=session.getAttribute("login_id")%>", "회원가입", "width=700, height=600, left=100, top=50"); 
	}
</script>

</head>
<body>
<div class = "login_form">


<form action="./memberLogoutAction.me" name = "logoutForm">
	<div class="card">
<%if (member_info.getMember_photo() == null){ %>		
		<img  src = "source/images/user.png" alt="John" style="width:200px; height: 200px; margin-top: -65px;">
<% }else{ %>
<img src="photo_down.jsp?photoName=<%=member_info.getMember_photo()%>"  style="width:200px; height: 200px; margin-top: -65px;
}">	
<% } %>	
		<h1 style="margin-top: 55px;color: black;"> 

		<img  src = "source/images/3.png"  class="rank"> 

			

		<%=session.getAttribute("login_id")%>님 환영합니다.</h1>
		
		<div class="btn" style = " margin-top: 17px; margin-right: 13px; margin-left: -3px;     width: 300px;">
			<p><a class="profile_btn" href="javascript:logoutForm.submit()" style = " color: white;">logout</a></p>
			<p><a class="profile_btn" href="" onclick = "showPopup();" style = " color: white;">My page</a></p>
		</div>
	</div>	
</form>
</div>


</body>
</html>