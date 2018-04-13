package mtm_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtm_svc.MtmBoardDeleteProService;
import mtm_vo.MtmActionForward;

public class MtmBoardDeleteProAction implements MtmAction {

	public MtmActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		MtmActionForward forward = null;
		int MTM_num=Integer.parseInt(request.getParameter("MTM_num"));
		String nowPage = request.getParameter("page");
		MtmBoardDeleteProService boardDeleteProService = new MtmBoardDeleteProService();
		boolean isArticleWriter =boardDeleteProService.isArticleWriter(MTM_num, request.getParameter("MTM_PASS"));

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
			
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(MTM_num);

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
				forward = new MtmActionForward();
				forward.setRedirect(true);
				forward.setPath("mtmboardList.mtmbo?page=" + nowPage);
			}
			
		}


		return forward;
	}

}