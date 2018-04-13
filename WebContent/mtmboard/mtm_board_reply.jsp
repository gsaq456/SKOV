<%@page import="mtm_vo.MtmBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	MtmBoardBean article=(MtmBoardBean)request.getAttribute("article");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>MVC 게시판</title>
<script language="javascript">
	</script>
<style type="text/css">
#registForm {
	width: 500px;
	height: 610px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

.td_left {
	width: 150px;
	background: orange;
}

.td_right {
	width: 300px;
	background: skyblue;
}

#commandCell {
	text-align: center;
}
</style>
</head>
<body>
	<!-- 게시판 답변 -->


	<section id="writeForm">
		<h2>게시판글등록</h2>
		<form action="mtmboardReplyPro.mtmbo" method="post" name="boardform">
			<input type="hidden" name="page" value="<%=nowPage %>" /> <input
				type="hidden" name="MTM_NUM" value="<%=article.getMTM_NUM() %>">
			<input type="hidden" name="MTM_RE_REF"
				value="<%=article.getMTM_RE_REF() %>"> <input
				type="hidden" name="MTM_RE_LEV"
				value="<%=article.getMTM_RE_LEV() %>"> <input
				type="hidden" name="MTM_RE_SEQ"
				value="<%=article.getMTM_RE_SEQ() %>">
			<table>
				<tr>
					<td class="td_left"><label for="MTM_NAME">글쓴이</label></td>
					<td class="td_right" style="text-align: center;"><%=session.getAttribute("login_id") %></td>
				</tr>
				<tr>
					<td class="td_left"><label for="MTM_PASS">비밀번호</label></td>
					<td class="td_right"><input name="MTM_PASS" type="password"
						id="MTM_PASS" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="MTM_SUBJECT">제 목</label></td>
					<td class="td_right"><input name="MTM_SUBJECT" type="text"
						id="MTM_SUBJECT" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="MTM_CONTENT">내 용</label></td>
					<td><textarea id="MTM_CONTENT" name="MTM_CONTENT"
							cols="40" rows="15"></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답변글등록" />&nbsp;&nbsp; <input
					type="reset" value="다시작성" />
			</section>
		</form>
	</section>
</body>
</html>