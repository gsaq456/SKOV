package grade_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grade_svc.Grade_ModifyProService;
import grade_vo.Grade_ActionForward;
import grade_vo.Grade_Bean;

public class Grade_ModifyProAction implements Grade_Action {

	public Grade_ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		Grade_ActionForward forward = null;
		boolean isModifySuccess = false;
		int up_num=Integer.parseInt(request.getParameter("UP_NUM"));
		Grade_Bean article=new Grade_Bean();
		Grade_ModifyProService Grade_ModifyProService = new Grade_ModifyProService();
		boolean isRightUser=Grade_ModifyProService.isArticleWriter(up_num, request.getParameter("UP_PASS"));

		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			article.setUP_NUM(up_num);
			article.setUP_SUBJECT(request.getParameter("UP_SUBJECT"));
			article.setUP_CONTENT(request.getParameter("UP_CONTENT")); 
			isModifySuccess = Grade_ModifyProService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new Grade_ActionForward();
				forward.setRedirect(true);
				forward.setPath("Grade_Detail.gradebo?up_num="+article.getUP_NUM()); 
			}

		}

		return forward;
	}

}