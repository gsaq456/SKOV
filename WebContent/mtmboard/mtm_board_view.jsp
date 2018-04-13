<%@page import="mtm_vo.MtmBoardBean"%>
<%@page import="vo.MemberBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%
	MtmBoardBean article = (MtmBoardBean)request.getAttribute("article");
    String nowPage = (String)request.getAttribute("page");
    MemberBean member_info = (MemberBean)session.getAttribute("member_info");
    String grade = member_info.getMember_grade();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
#articleForm {
	width: 500px;
	height: 500px;
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
	height: 350px;
	text-align: center;
	overflow: auto;
}

#commandList {
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
			<%=article.getMTM_SUBJECT()%><br>
				첨부파일 :
			<%if(!(article.getMTM_FILE()==null)){ %>
			<img src="mtmboard/mtm_file_down.jsp?file_name=<%=article.getMTM_FILE()%>"/> 
			파일 다운로드 :
			<a href="mtmboard/mtm_file_down.jsp?file_name=<%=article.getMTM_FILE()%>">
			<%=article.getMTM_FILE() %></a>
			<%} %>
		</section>
		<section id="articleContentArea">
			<%=article.getMTM_CONTENT() %>
		</section>
	</section>
	<section id="commandList">
<%
	if(grade.equals("전문가")){
	
%>
		<a
			href="mtmboardReplyForm.mtmbo?MTM_num=<%=article.getMTM_NUM() %>&page=<%=nowPage%>">
			[답변] </a> 
<%
	}
%>		
		<a
			href="mtmboardModifyForm.mtmbo?MTM_num=<%=article.getMTM_NUM() %>">
			[수정] </a> <a
			href="mtmboardDeleteForm.mtmbo?MTM_num=<%=article.getMTM_NUM() %>&page=<%=nowPage%>">
			[삭제] </a> 
			<a href="mtmboardList.mtmbo?page=<%=nowPage%>">[목록]</a>&nbsp;&nbsp;
	</section>
</body>
</html>