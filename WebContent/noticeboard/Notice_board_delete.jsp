<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	int notice_num=(Integer)request.getAttribute("notice_num");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>MVC 게시판</title>
<style>

	#passForm{
		width:400px;
		margin:auto;
		border : 1px solid orange;
	}
	
</style>
</head>
<body>
<section id = "passForm">
<form name="deleteForm" action="noticeboardDeletePro.noticebo?notice_num=<%=notice_num %>" 
	method="post">
<input type = "hidden" name = "page" value = "<%=nowPage %>"/>
<table>
<tr>
	<td>
		<label>글 비밀번호 : </label>
	</td>
	<td>
		<input name="NOTICE_PASS" type="password">
	</td>
</tr>
<tr>
	<td>
		<input type="submit" value = "삭제"/>
		&nbsp;&nbsp;
		<input type = "button" value = "돌아가기" onClick ="javascript:history.go(-1)"/>
	</td>
</tr>
</table>
</form>
</section>
</body>
</html>