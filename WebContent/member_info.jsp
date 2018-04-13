<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "vo.MemberBean" %>

<%
	@SuppressWarnings("unchecked")
	MemberBean member = (MemberBean)session.getAttribute("member_info");
    
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
* {
  margin: 0;
  padding: 0;
  -webkit-box-sizing: border-box;
  	 -moz-box-sizing: border-box;
      -ms-box-sizing: border-box;
       -o-box-sizing: border-box;
          box-sizing: border-box;
  
}

body {
    font-family: 'Roboto', sans-serif;
    background: #f9f9f9;
    background-size: cover;
    background-position: center;
    position: relative;
    background-attachment: fixed;
}

body:before {
    content: '';
    display: block;
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    z-index: -1;
}

.container {
    max-width: 900px;
		width: calc(100% - 16px);
    display: block;
    background: #fff;
    overflow: hidden;
    margin: 32px auto;
    -moz-box-shadow: 0px 5px 28px rgba(0, 0, 0, 0.2);
    -webkit-box-shadow: 0px 5px 28px rgba(0, 0, 0, 0.2);
    box-shadow: 0px 5px 28px rgba(0, 0, 0, 0.2);
    border-radius: 3px;
}

/*------------header section --------------------*/
header {
    position: relative;
    display: block;
    height: 250px;
    background: #3F51B5;
    z-index: 1;
}
.content {
	display: block;
}

.aside-01 {
  z-index: 2;
  text-align: center;
}

.profile .image {
    background: white;
   	background-size: cover;
    border-radius: 60%;
    width: 150px;
    height: 150px;
    display: block;
    margin: -78px auto 0;
    position: relative;
		z-index: 2;
}

.profile .image:after {
    content: '';
    display: block;
    width: 16px;
    height: 16px;
    position: absolute;
    top: 101px;
    left: 84px;
    border: 1.5px solid #fff;
    background: #009688;
    border-radius: 50%;
}

.aside-02 {
  text-align: center;
}

.profile h4,
.profile span {
  margin-top: 24px;
  line-height: 24px;
}

.profile h4 {
  color: rgba(0, 0, 0, 0.8);
  font-size: 1em;
  font-weight: 800;
}

.profile span {
  font-size: 14px;
  color: rgba(0, 0, 0, 0.6);
}


</style>

</head>
<body>
<%--  --%>
<%-- <h3>member_info.jsp 파일 입니다.</h3>
아이디 : ${member.member_id}		<br>
비밀번호 : ${member.member_pw}		<br>
이름 : ${member.member_name}		<br>
나이 : ${member.member_age}		<br>
성별 : ${member.member_gender}	<br>
이메일 : ${member.member_email}	<br>
주소 : ${member.member_addr}		<br>
피부타입 : ${member.member_skin}	<br>
등급 : ${member.member_grade}		<br>  --%>





<div class="container">
  <header>
  
  </header>
  <div class="content">
   <aside class="aside-01 col-md-5">
    <div class="profile">
      <div  class="image">
<% 
	String member_photo = member.getMember_photo();
	if(member_photo == null){ %>
      	<img src="source/images/user.png" style=" width: 130px; height: 130px; margin-left: 15px;">
<% }else{ %>
		<img src="photo_down.jsp?photoName=<%=member.getMember_photo()%>"  style="border-radius: 60%; width: 130px; height: 130px; ">
<% } %>
      </div>
      <h4>
	
	아이디 : ${member.member_id}		<br>
	이름 : ${member.member_name}		<br>
	나이 : ${member.member_age}		<br>
	성별 : ${member.member_gender}	<br>
	이메일 : ${member.member_email}	<br>
	주소 : ${member.member_addr}		<br>
	피부타입 : ${member.member_skin}	<br>
	등급 : ${member.member_grade}</h4><br>
	
	<a href = 'memberInfoModifyForm.me' style="display: block;">회원 정보수정</a> <br><br>
	
</div>
</aside>
</div>
    <% if(session.getAttribute("login_id").equals("admin")){ %>

<form action="./memberGradeAction.me" method="post" name = "gradeForm">
				<br/><select name = "grade">
					<option value = "일반" selected="selected">일반</option>
					<option value = "전문가">전문가</option>
					<option value = "관계자">관계자</option>
				</select>
				<a href = "javascript:gradeForm.submit()">등급 수정하기</a> 
</form>

<br/><br/><a href="memberListAction.me">회원 목록보기</a>

<% } %>
</div>


</body>
</html>