package report_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report_svc.ReportBoardModifyProService;
import report_vo.ReportActionForward;
import report_vo.ReportBoardBean;

public class ReportBoardModifyProAction implements ReportAction {

	public ReportActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		ReportActionForward forward = null;
		boolean isModifySuccess = false;
		int REPORT_num=Integer.parseInt(request.getParameter("REPORT_NUM"));
		ReportBoardBean article=new ReportBoardBean();
		ReportBoardModifyProService boardModifyProService = new ReportBoardModifyProService();
		boolean isRightUser=boardModifyProService.isArticleWriter(REPORT_num, request.getParameter("REPORT_PASS"));

		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			article.setREPORT_NUM(REPORT_num);
			article.setREPORT_SUBJECT(request.getParameter("REPORT_SUBJECT"));
			article.setREPORT_CONTENT(request.getParameter("REPORT_CONTENT")); 
			isModifySuccess = boardModifyProService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ReportActionForward();
				forward.setRedirect(true);
				forward.setPath("reportboardDetail.reportbo?REPORT_num="+article.getREPORT_NUM()); 
			}

		}

		return forward;
	}

}