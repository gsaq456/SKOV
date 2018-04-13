package mtm_action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mtm_svc.MtmBoardWriteProService;
import mtm_vo.MtmActionForward;
import mtm_vo.MtmBoardBean;

public class MtmBoardWriteProAction implements MtmAction {

	public MtmActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		MtmActionForward forward=null;
		MtmBoardBean boardBean = null;
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
		
		boardBean = new MtmBoardBean();
		boardBean.setMTM_NAME(multi.getParameter("MTM_NAME"));
		boardBean.setMTM_PASS(multi.getParameter("MTM_PASS"));
		boardBean.setMTM_SUBJECT(multi.getParameter("MTM_SUBJECT"));
		boardBean.setMTM_CONTENT(multi.getParameter("MTM_CONTENT"));
		boardBean.setMTM_FILE(
		multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		MtmBoardWriteProService boardWriteProService = new MtmBoardWriteProService();
		boolean isWriteSuccess = boardWriteProService.registArticle(boardBean);

		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new MtmActionForward();
			forward.setRedirect(true);
			forward.setPath("mtmboardList.mtmbo");
		}

		return forward;
		
	}  	
	
}