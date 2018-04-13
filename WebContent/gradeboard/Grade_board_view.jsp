<%@page import="grade_vo.Grade_Bean"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%
	Grade_Bean article = (Grade_Bean)request.getAttribute("article");
    String nowPage = (String)request.getAttribute("page");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">

#articleForm {
   width: 540px;
   height: 200%;
   border: 1px solid red;
   margin: auto;
   margin-top: 30px; 
   padding: 10px 10px 10px 10px;
   background: #c9d0de;
   border: 1px solid #e1e1e1;
   -moz-box-shadow: 0px 0px 8px #444;
   -webkit-box-shadow: 0px 0px 8px #444;}
}

#basicInfoArea {
   height: 40px;
   text-align: center;
}

#articleContentArea {
   text-align: center;
   margin-left:auto;
   margin-right:auto;
   width: 450px; 
   height: 200px; 
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
   
}


   
textarea {
   margin-left:6px;
   width: 400px; 
   height: 80px; 
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
   
.file_img{
	position: absolute;
}
</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js">
//<![CDATA[
$(document).ready(function(){
	$(".file_text").mouseenter(function(){
		$(".file_img").css("display","block");
	});
	$(".file_text").mouseleave(function(){
		$(".file_img").css("display","none");
	});
});

//]]>
</script>

</head>

<body>
	<!-- 게시판 수정 -->
	<section id="articleForm" style="height: 650px;">
		<h2>글 내용 상세보기</h2>
		<section id="basicInfoArea">
			제 목 :
			<%=article.getUP_SUBJECT()%> <br>

			
		</section>
		<section id="articleContentArea" style="height: 500px;">
			<%=article.getUP_CONTENT() %>
		</section>

		
	</section>
	<section style="margin-left:850px;">
		<a href="Grade_ModifyForm.gradebo?up_num=<%=article.getUP_NUM() %>&page=<%=nowPage%>">
			[수정] </a> <a
			href="Grade_DeleteForm.gradebo?up_num=<%=article.getUP_NUM() %>&page=<%=nowPage%>">
			[삭제] </a> <a href="Grade_List.gradebo">[목록]</a>&nbsp;&nbsp;
	</section>
	
</body>
</html>