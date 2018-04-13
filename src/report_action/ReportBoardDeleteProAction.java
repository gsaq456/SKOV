package report_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report_svc.ReportBoardDeleteProService;
import report_vo.ReportActionForward;

public class ReportBoardDeleteProAction implements ReportAction {

	public ReportActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		ReportActionForward forward = null;
		int REPORT_num=Integer.parseInt(request.getParameter("REPORT_num"));
		String nowPage = request.getParameter("page");
		ReportBoardDeleteProService boardDeleteProService = new ReportBoardDeleteProService();
		boolean isArticleWriter =boardDeleteProService.isArticleWriter(REPORT_num, request.getParameter("REPORT_PASS"));

		if(!isArticleWriter){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		else{
			
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(REPORT_num);

			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forward = new ReportActionForward();
				forward.setRedirect(true);
				forward.setPath("reportboardList.reportbo?page=" + nowPage);
			}
			
		}


		return forward;
	}

}