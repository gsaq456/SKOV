package report_action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import report_svc.ReportBoardWriteProService;
import report_vo.ReportActionForward;
import report_vo.ReportBoardBean;

public class ReportBoardWriteProAction implements ReportAction {

	public ReportActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		ReportActionForward forward=null;
		ReportBoardBean boardBean = null;
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=40*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);   		
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		boardBean = new ReportBoardBean();
		boardBean.setREPORT_NAME(multi.getParameter("REPORT_NAME"));
		boardBean.setREPORT_PASS(multi.getParameter("REPORT_PASS"));
		boardBean.setREPORT_HEAD(multi.getParameter("REPORT_HEAD"));
		boardBean.setREPORT_SUBJECT(multi.getParameter("REPORT_SUBJECT"));
		boardBean.setREPORT_CONTENT(multi.getParameter("REPORT_CONTENT"));
		boardBean.setREPORT_FILE(
		multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		ReportBoardWriteProService boardWriteProService = new ReportBoardWriteProService();
		boolean isWriteSuccess = boardWriteProService.registArticle(boardBean);

		if(isWriteSuccess){
			forward = new ReportActionForward();
			forward.setRedirect(true);
			forward.setPath("reportboardList.reportbo");
			
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