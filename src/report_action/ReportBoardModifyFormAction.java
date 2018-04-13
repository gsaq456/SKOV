package report_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report_svc.ReportBoardDetailService;
import report_vo.ReportActionForward;
import report_vo.ReportBoardBean;

public class ReportBoardModifyFormAction implements ReportAction {
	
	 public ReportActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ReportActionForward forward = new ReportActionForward();
			int REPORT_num=Integer.parseInt(request.getParameter("REPORT_num"));
			ReportBoardDetailService boardDetailService
			= new ReportBoardDetailService();	
		   	ReportBoardBean article =boardDetailService.getArticle(REPORT_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/main_View.jsp?main_page=report_modify");
	   		return forward;
	   		
	 }
	 
}