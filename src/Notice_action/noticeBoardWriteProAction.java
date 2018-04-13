package Notice_action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Notice_svc.noticeBoardWriteProService;
import Notice_vo.noticeActionForward;
import Notice_vo.noticeBoardBean;

public class noticeBoardWriteProAction implements noticeAction {

	public noticeActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		noticeActionForward forward=null;
		noticeBoardBean noticeboardBean = null;
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=100*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);   		
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		noticeboardBean = new noticeBoardBean();
		noticeboardBean.setNOTICE_NAME(multi.getParameter("NOTICE_NAME"));
		noticeboardBean.setNOTICE_PASS(multi.getParameter("NOTICE_PASS"));
		noticeboardBean.setNOTICE_SUBJECT(multi.getParameter("NOTICE_SUBJECT"));
		noticeboardBean.setNOTICE_CONTENT(multi.getParameter("NOTICE_CONTENT"));
		
		noticeBoardWriteProService noticeboardWriteProService = new noticeBoardWriteProService();
		boolean isWriteSuccess = noticeboardWriteProService.registArticle(noticeboardBean);
		
		System.out.println("노티스 보드빈 " + noticeboardBean);
		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new noticeActionForward();
			forward.setRedirect(true);
			forward.setPath("noticeboardList.noticebo");
		}

		return forward;
		
	}  	
	
}