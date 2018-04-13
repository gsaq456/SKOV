<%@page import="Notice_vo.noticeBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	noticeBoardBean article = (noticeBoardBean)request.getAttribute("article");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
	<title>MVC 게시판</title>
	<script type="text/javascript">
	function modifyboard(){
		modifyform.submit();
	}
	</script>
	<style type="text/css">
   #registForm{
      width: 500px;
      height: 600px;
      border : 1px solid red; 
      margin:auto; 
   }   
   h2{
      text-align: center;
   }
   table{
      margin:auto;
      width: 450px;
      }
   .td_left{
      width: 150px;
      background:orange;
   }
   .td_right{
      width: 300px;
      background:skyblue;
   }
   #commandCell{
      text-align: center;
      
   }
</style>
</head>
<body>
<!-- 게시판 등록 -->

<section id = "writeForm">
<h2>게시판글수정</h2>
<form action="noticeboardModifyPro.noticebo" method="post" name = "noticemodifyform"
>
<input type = "hidden" name = "NOTICE_NUM" value = "<%=article.getNOTICE_NUM()%>"/>
<table>
	<tr>
		<td class="td_left">
			<label for = "NOTICE_NAME">글쓴이</label>
		</td>
		<td class="td_right">
			<input type = "text" name="NOTICE_NAME" id = "NOTICE_NAME" value = "<%=article.getNOTICE_NAME()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "NOTICE_PASS">비밀번호</label>
		</td>
		<td class="td_right">
			<input name="NOTICE_PASS" type="password" id = "NOTICE_PASS"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "NOTICE_SUBJECT">제 목</label>
		</td>
		<td class="td_right">
			<input name="NOTICE_SUBJECT" type="text" id = "NOTICE_SUBJECT" value = "<%=article.getNOTICE_SUBJECT()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "NOTICE_CONTENT">내 용</label>
		</td>
		<td>
			<textarea id = "NOTICE_CONTENT" name="NOTICE_CONTENT" cols="40" rows="15"><%=article.getNOTICE_CONTENT()%></textarea>
		</td>
	</tr>
</table>
	<section id = "commandCell">
			<a href="javascript:noticemodifyform.submit()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>
	</section>
</form>
</section>
</body>
</html>