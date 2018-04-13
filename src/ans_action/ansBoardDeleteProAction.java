package ans_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ans_svc.ansBoardDeleteProService;
import ans_vo.ansActionForward;

public class ansBoardDeleteProAction implements ansAction {

	public ansActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		ansActionForward forward = null;
		int ans_num=Integer.parseInt(request.getParameter("ans_num"));
		String nowPage = request.getParameter("page");
		ansBoardDeleteProService ansboardDeleteProService = new ansBoardDeleteProService();
		boolean isArticleWriter =ansboardDeleteProService.isArticleWriter(ans_num, request.getParameter("ANS_PASS"));

		if(!isArticleWriter){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		else{
			
			boolean isDeleteSuccess = ansboardDeleteProService.removeArticle(ans_num);

			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forward = new ansActionForward();
				forward.setRedirect(true);
				forward.setPath("ansboardList.ansbo?page=" + nowPage);
			}
			
		}


		return forward;
	}

}