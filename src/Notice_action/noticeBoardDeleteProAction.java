package Notice_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Notice_svc.noticeBoardDeleteProService;
import Notice_vo.noticeActionForward;

public class noticeBoardDeleteProAction implements noticeAction {

	public noticeActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		noticeActionForward forward = null;
		int notice_num=Integer.parseInt(request.getParameter("notice_num"));
		String nowPage = request.getParameter("page");
		noticeBoardDeleteProService noticeboardDeleteProService = new noticeBoardDeleteProService();
		boolean isArticleWriter =noticeboardDeleteProService.isArticleWriter(notice_num, request.getParameter("NOTICE_PASS"));

		if(!isArticleWriter){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제성공');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		else{
			
			boolean isDeleteSuccess = noticeboardDeleteProService.removeArticle(notice_num);

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
				forward = new noticeActionForward();
				forward.setRedirect(true);
				forward.setPath("noticeboardList.noticebo?page=" + nowPage);
			}
			
		}


		return forward;
	}

}