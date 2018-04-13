<%@page import="vo.BoardBean"%>
<%@page import="vo.ReplyBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
   BoardBean article = (BoardBean)request.getAttribute("article");
   
    String nowPage = (String)request.getAttribute("page");
    
    @SuppressWarnings("unchecked")
   ArrayList<ReplyBean> replyList=(ArrayList<ReplyBean>)request.getAttribute("replyList");
   
    
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

#reple {
   background: pink;
   margin: auto;
   width: 500px;
   text-align: center;
}

#reple2 {
   margin: auto;
   width: 500px;
   text-align: center;
}

input[type=submit] {
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
   
input[type=reset] {
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
   <section id="articleForm">
      <h2 style="text-align:center;">글 내용 상세보기</h2>
      <section id="basicInfoArea">
         제 목 :
         <%=article.getFB_SUBJECT()%> <br>
       
		<%if(!(article.getFB_FILE()==null)){ %>
		<div style="float: right;">
			<h3 class = "file_text">첨부파일</h3>
			<img class = "file_img" style="display: none;" src="freeboard/file_down.jsp?file_name=<%=article.getFB_FILE()%>"/> 
		</div>
		
         파일 다운로드 :
         <a href="freeboard/file_down.jsp?file_name=<%=article.getFB_FILE()%>">
         <%=article.getFB_FILE() %></a>
         <%} %>
         
      </section>
      <br><br>
      <section id="articleContentArea">
         <%=article.getFB_CONTENT() %>
      </section>
      
      <%if(session.getAttribute("login_id") != null) { %>
      
      <section id="reply" style="text-align: center;">
         <form action="boardReplyWritePro.bo" method = "post">
            <input type="hidden" name = "FB_num"value = "<%=request.getParameter("FB_num") %>">
            <input type="hidden" name = "page"   value = "<%=request.getParameter("page") %>">
            <table>
               <tr>
                  <td>댓글</td>
                  <td><textarea rows="5" cols="65" name = "FB_REPLY"></textarea></td>
               </tr>
               <tr>
                  <td colspan="2"><input type = "submit" value = "전송">   <input type = "reset" value ="다시쓰기"></td>
               </tr>
               <tr>
                  <td>
                     
                  </td>
               </tr>
            </table>
         </form>
      </section>
      <%} %>
      
      <section id="replyArea">
         <table>
   <%
      for(int i=0;i<replyList.size();i++){
   %>      
            <tr>
            
               <td>
                  <%=replyList.get(i).getM_ID() %>
               </td>
      
               <td>
                  <%=replyList.get(i).getFB_REPLY() %>
               </td>
               <td>
                  <%=replyList.get(i).getREPLY_DATE() %>
               </td>
               
            </tr>
   <% } %>
         </table>
      </section>
         
   </section>
   <section style="margin-left:850px;"><br/><br/>
      <a
         href="boardModifyForm.bo?FB_num=<%=article.getFB_NUM() %>">
         [수정] </a> <a
         href="boardDeleteForm.bo?FB_num=<%=article.getFB_NUM() %>&page=<%=nowPage%>">
         [삭제] </a> 
         <a href="./boardList.bo">[목록]</a>&nbsp;&nbsp;
   </section>
   
   
</body>
</html>