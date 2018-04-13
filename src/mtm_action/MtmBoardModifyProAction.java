package mtm_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtm_svc.MtmBoardModifyProService;
import mtm_vo.MtmActionForward;
import mtm_vo.MtmBoardBean;

public class MtmBoardModifyProAction implements MtmAction {

	public MtmActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		MtmActionForward forward = null;
		boolean isModifySuccess = false;
		int MTM_num=Integer.parseInt(request.getParameter("MTM_NUM"));
		MtmBoardBean article=new MtmBoardBean();
		MtmBoardModifyProService boardModifyProService = new MtmBoardModifyProService();
		boolean isRightUser=boardModifyProService.isArticleWriter(MTM_num, request.getParameter("MTM_PASS"));

		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			article.setMTM_NUM(MTM_num);
			article.setMTM_SUBJECT(request.getParameter("MTM_SUBJECT"));
			article.setMTM_CONTENT(request.getParameter("MTM_CONTENT")); 
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
				forward = new MtmActionForward();
				forward.setRedirect(true);
				forward.setPath("mtmboardDetail.mtmbo?MTM_num="+article.getMTM_NUM()); 
			}

		}

		return forward;
	}

}