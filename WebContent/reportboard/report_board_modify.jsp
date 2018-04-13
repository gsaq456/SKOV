<%@page import="report_vo.ReportBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	ReportBoardBean article = (ReportBoardBean)request.getAttribute("article");
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
<form action="reportboardModifyPro.reportbo" method="post" name = "modifyform"
>
<input type = "hidden" name = "REPORT_NUM" value = "<%=article.getREPORT_NUM()%>"/>
<table>
	<tr>
		<td class="td_left">
			<label for = "REPORT_NAME">글쓴이</label>
		</td>
		<td class="td_right">
			<input type = "text" name="REPORT_NAME" id = "REPORT_NAME" value = "<%=article.getREPORT_NAME()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "REPORT_PASS">비밀번호</label>
		</td>
		<td class="td_right">
			<input name="REPORT_PASS" type="password" id = "REPORT_PASS"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "REPORT_SUBJECT">제 목</label>
		</td>
		<td class="td_right">
			<input name="REPORT_SUBJECT" type="text" id = "REPORT_SUBJECT" value = "<%=article.getREPORT_SUBJECT()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "REPORT_CONTENT">내 용</label>
		</td>
		<td>
			<textarea id = "REPORT_CONTENT" name="REPORT_CONTENT" cols="40" rows="15"><%=article.getREPORT_CONTENT()%></textarea>
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