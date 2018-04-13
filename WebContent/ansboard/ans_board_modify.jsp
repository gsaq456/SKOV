<%@page import="ans_vo.ansBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	ansBoardBean article = (ansBoardBean)request.getAttribute("article");
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
<form action="ansboardModifyPro.ansbo" method="post" name = "ansmodifyform"
>
<input type = "hidden" name = "ANS_NUM" value = "<%=article.getANS_NUM()%>"/>
<table>
	<tr>
		<td class="td_left">
			<label for = "ANS_NAME">글쓴이</label>
		</td>
		<td class="td_right">
			<input type = "text" name="ANS_NAME" id = "ANS_NAME" value = "<%=article.getANS_NAME()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ANS_PASS">비밀번호</label>
		</td>
		<td class="td_right">
			<input name="ANS_PASS" type="password" id = "ANS_PASS"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ANS_SUBJECT">제 목</label>
		</td>
		<td class="td_right">
			<input name="ANS_SUBJECT" type="text" id = "ANS_SUBJECT" value = "<%=article.getANS_SUBJECT()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "ANS_CONTENT">내 용</label>
		</td>
		<td>
			<textarea id = "ANS_CONTENT" name="ANS_CONTENT" cols="40" rows="15"><%=article.getANS_CONTENT()%></textarea>
		</td>
	</tr>
</table>
	<section id = "commandCell">
			<a href="javascript:ansmodifyform.submit()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>
	</section>
</form>
</section>
</body>
</html>