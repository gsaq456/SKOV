package grade_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grade_svc.Grade_DeleteProService;
import grade_vo.Grade_ActionForward;

public class Grade_DeleteProAction implements Grade_Action {



	public Grade_ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		Grade_ActionForward forward = null;
		int up_num=Integer.parseInt(request.getParameter("up_num"));
		String nowPage = request.getParameter("page");
		Grade_DeleteProService Grade_DeleteProService = new Grade_DeleteProService();
		boolean isArticleWriter =Grade_DeleteProService.isArticleWriter(up_num, request.getParameter("UP_PASS"));

		if(!isArticleWriter){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		else{
			
			boolean isDeleteSuccess = Grade_DeleteProService.removeArticle(up_num);

			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('�궘�젣�떎�뙣');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forward = new Grade_ActionForward();
				forward.setRedirect(true);
				forward.setPath("Grade_List.gradebo?page=" + nowPage);
			}
			
		}


		return forward;
	}

}