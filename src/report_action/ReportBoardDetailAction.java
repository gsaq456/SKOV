package report_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report_svc.ReportBoardDetailService;
import report_vo.ReportActionForward;
import report_vo.ReportBoardBean;

 public class ReportBoardDetailAction implements ReportAction {
	 
	 public ReportActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int REPORT_num=Integer.parseInt(request.getParameter("REPORT_num"));
		String page = request.getParameter("page");
		ReportBoardDetailService boardDetailService = new ReportBoardDetailService();
		ReportBoardBean article = boardDetailService.getArticle(REPORT_num);
		ReportActionForward forward = new ReportActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/main_View.jsp?main_page=report_write_view");
   		return forward;

	 }
	 
}