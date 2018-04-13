<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
 
 <style type="text/css">

body, div, h1, form, fieldset, input, textarea {
   margin: 0; 
   padding: 0; 
   border: 0; 
   outline: none;}

html {height: 100%;}

#contact {
   width: 420px; 
   margin: 60px auto; 
   padding: 10px 10px 10px 10px;
   background: #c9d0de;
   border: 1px solid #e1e1e1;
   -moz-box-shadow: 0px 0px 8px #444;
   -webkit-box-shadow: 0px 0px 8px #444;}

h1 {font-size: 35px; 
   color: #445668; 
   text-transform: uppercase;
   text-align: center; 
   margin: 0 0 35px 0; 
   text-shadow: 0px 1px 0px #f2f2f2;}

label {
   float: left; 
   clear: left; 
   margin: 11px 20px 0 0; 
   width: 95px;
   text-align: right; 
   font-size: 16px; 
   color: #445668; 
   text-shadow: 0px 1px 0px #f2f2f2;}



fieldset input {
   width: 260px; 
   height: 35px; 
   padding: 5px 20px 0px 20px; 
   margin: 0 0 20px 0; 
   background: #5E768D;
   border-radius: 5px; 
   -moz-border-radius: 5px;
   -moz-box-shadow: 0px 1px 0px #f2f2f2;
   -webkit-box-shadow: 0px 1px 0px #f2f2f2;
   font-family: sans-serif; 
   font-size: 16px; 
   color: #f2f2f2; 
   text-shadow: 0px -1px 0px #334f71;}

fieldset textarea {
   width: 260px; 
   height: 170px; 
   padding: 12px 20px 0px 20px;
   margin: 0 0 20px 0; 
   background: #5E768D;
   border-radius: 5px; 
   -moz-border-radius: 5px; 
   -webkit-border-radius: 5px;
   -moz-box-shadow: 0px 1px 0px #f2f2f2;
   -webkit-box-shadow: 0px 1px 0px #f2f2f2;
   font-family: sans-serif; 
   font-size: 16px; 
   color: #f2f2f2; 
   text-shadow: 0px -1px 0px #334f71;}

fieldset input:focus, textarea:focus {
   background: #728eaa;
   background: -webkit-gradient(linear, left top, left bottom, 
   color-stop(0%,#668099), color-stop(20%,#728eaa));}

fieldset input[type=submit] {
   width: 150px; 
   height: 50px; 
   float: right; 
   padding: 10px 15px; 
   margin: 0 3px 0 0;
   -moz-box-shadow: 0px 0px 5px #999;
   -webkit-box-shadow: 0px 0px 5px #999;
   border: 1px solid #556f8c;
   background: -webkit-gradient(linear, left top, left bottom, 
   color-stop(0%,#718DA9), color-stop(100%,#415D79));
   cursor: pointer;}
   
fieldset input[type=reset] {
   width: 150px; 
   height: 50px; 
   float: right; 
   padding: 10px 15px; 
   margin: 0 3px 0 0;
   -moz-box-shadow: 0px 0px 5px #999;
   -webkit-box-shadow: 0px 0px 5px #999;
   border: 1px solid #556f8c;
   background: -webkit-gradient(linear, left top, left bottom, 
   color-stop(0%,#718DA9), color-stop(100%,#415D79));
   cursor: pointer;}


</style>
</head>
<body>
	<!-- 게시판 등록 -->
<div id="contact">
   <h1>게시판 글등록</h1>
   <form action="ansboardWritePro.ansbo" method="post"
			enctype="multipart/form-data" name="boardform">
      <fieldset>
      	 
         <input type="hidden" name="ANS_NAME" id="ANS_NAME" value =<%=session.getAttribute("login_id") %> />
         <div style="height: 40px; text-align: center;">
         <label for="ANS_NAME" style="margin-top: 5px;">글쓴이</label><%=session.getAttribute("login_id") %>
         </div>

         <label for="ANS_PASS">비밀번호</label>
         <input name="ANS_PASS" type="password"
						id="ANS_PASS" required="required"/>
     
         <label for="ANS_SUBJECT">제 목</label>
         <input name="ANS_SUBJECT" type="text"
						id="ANS_SUBJECT" required="required" />
         
         <label for="ANS_CONTENT">내 용</label>
         <textarea id="ANS_CONTENT" name="ANS_CONTENT"
							cols="40" rows="15" required="required"></textarea>
      
         <input type="reset" value="다시쓰기"/>
         <input type="submit" value="등록" />
         
      </fieldset>
   </form>
</div>

	<!-- 게시판 등록 -->
</body>
</html>