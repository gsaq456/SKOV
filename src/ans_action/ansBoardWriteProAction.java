package ans_action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ans_svc.ansBoardWriteProService;
import ans_vo.ansActionForward;
import ans_vo.ansBoardBean;

public class ansBoardWriteProAction implements ansAction {

	public	ansActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		ansActionForward forward=null;
		ansBoardBean ansboardBean = null;
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
		ansboardBean = new ansBoardBean();
		ansboardBean.setANS_NAME(multi.getParameter("ANS_NAME"));
		ansboardBean.setANS_PASS(multi.getParameter("ANS_PASS"));
		ansboardBean.setANS_SUBJECT(multi.getParameter("ANS_SUBJECT"));
		ansboardBean.setANS_CONTENT(multi.getParameter("ANS_CONTENT"));
		
		ansBoardWriteProService ansboardWriteProService = new ansBoardWriteProService();
		boolean isWriteSuccess = ansboardWriteProService.registArticle(ansboardBean);
		
		System.out.println("노티스 보드빈 " + ansboardBean);
		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new ansActionForward();
			forward.setRedirect(true);
			forward.setPath("ansboardList.ansbo");
		}

		return forward;
		
	}  	
	
}