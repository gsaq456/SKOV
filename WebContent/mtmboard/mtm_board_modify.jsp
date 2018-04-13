<%@page import="mtm_vo.MtmBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	MtmBoardBean article = (MtmBoardBean)request.getAttribute("article");
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
<form action="mtmboardModifyPro.mtmbo" method="post" name = "modifyform"
>
<input type = "hidden" name = "MTM_NUM" value = "<%=article.getMTM_NUM()%>"/>
<table>
	<tr>
		<td class="td_left">
			<label for = "BOARD_NAME">글쓴이</label>
		</td>
		<td class="td_right">
			<input type = "text" name="MTM_NAME" id = "MTM_NAME" value = "<%=article.getMTM_NAME()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "MTM_PASS">비밀번호</label>
		</td>
		<td class="td_right">
			<input name="MTM_PASS" type="password" id = "MTM_PASS"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "MTM_SUBJECT">제 목</label>
		</td>
		<td class="td_right">
			<input name="MTM_SUBJECT" type="text" id = "MTM_SUBJECT" value = "<%=article.getMTM_SUBJECT()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "MTM_CONTENT">내 용</label>
		</td>
		<td>
			<textarea id = "MTM_CONTENT" name="MTM_CONTENT" cols="40" rows="15"><%=article.getMTM_CONTENT()%></textarea>
		</td>
	</tr>
</table>
	<section id = "commandCell">
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>
	</section>
</form>
</section>
</body>
</html>