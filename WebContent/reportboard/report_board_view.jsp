<%@page import="report_vo.ReportBoardBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%
	ReportBoardBean article = (ReportBoardBean)request.getAttribute("article");
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
}

h2 {
	text-align: center;
}

#basicInfoArea {
	height: 40px;
	text-align: center;
}

#articleContentArea {
	background: orange;
	margin-top: 20px;
	height: 550px;
	text-align: center;
	overflow: auto;
}

#commandList {
	margin: auto;
	width: 500px;
	text-align: center;
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

</style>
</head>

<body>
	<!-- 게시판 수정 -->
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<section id="basicInfoArea">
			제 목 :
			<%=article.getREPORT_SUBJECT()%> <br>
			첨부파일 :
			<%if(!(article.getREPORT_FILE()==null)){ %>
			<img src="reportboard/report_file_down.jsp?file_name=<%=article.getREPORT_FILE()%>"/> 
			파일 다운로드 :
			<a href="reportboard/report_file_down.jsp?file_name=<%=article.getREPORT_FILE()%>">
			<%=article.getREPORT_FILE() %></a>
			<%} %>
			
		</section>
		<section id="articleContentArea">
			<%=article.getREPORT_CONTENT() %>
		</section>

		
	</section>
	<section id="commandList">
		<a
			href="reportboardModifyForm.reportbo?REPORT_num=<%=article.getREPORT_NUM() %>">
			[수정] </a> <a
			href="reportboardDeleteForm.reportbo?REPORT_num=<%=article.getREPORT_NUM() %>&page=<%=nowPage%>">
			[삭제] </a> 
			<a href="./reportboardList.reportbo">[목록]</a>&nbsp;&nbsp;
	</section>
	
</body>
</html>