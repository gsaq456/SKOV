package grade_action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import grade_svc.Grade_WriteProService;
import grade_vo.Grade_ActionForward;
import grade_vo.Grade_Bean;

public class Grade_WriteProAction implements Grade_Action {

	public Grade_ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		Grade_ActionForward forward=null;
		Grade_Bean article = null;
		
		   		
		article = new Grade_Bean();
		
		article.setUP_NAME(request.getParameter("UP_NAME"));
		article.setUP_PASS(request.getParameter("UP_PASS"));	
		article.setUP_SUBJECT(request.getParameter("UP_SUBJECT"));
		article.setUP_CONTENT(request.getParameter("UP_CONTENT"));
		
		
		System.out.println(request.getParameter("UP_CONTENT"));
		System.out.println(article.getUP_CONTENT());
		
		Grade_WriteProService Grade_WriteProService = new Grade_WriteProService();
		boolean isWriteSuccess = Grade_WriteProService.registArticle(article);

		if(isWriteSuccess){
			forward = new Grade_ActionForward();
			forward.setRedirect(true);
			forward.setPath("Grade_List.gradebo");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}

		return forward;
		
	}  	
	
}