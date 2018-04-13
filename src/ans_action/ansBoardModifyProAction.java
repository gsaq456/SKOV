package ans_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ans_svc.ansBoardModifyProService;
import ans_vo.ansActionForward;
import ans_vo.ansBoardBean;

public class ansBoardModifyProAction implements ansAction {

	public ansActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		ansActionForward forward = null;
		boolean isModifySuccess = false;
		int ans_num=Integer.parseInt(request.getParameter("ANS_NUM"));
		ansBoardBean article=new ansBoardBean();
		ansBoardModifyProService ansboardModifyProService = new ansBoardModifyProService();
		boolean isRightUser= ansboardModifyProService.isArticleWriter(ans_num, request.getParameter("ANS_PASS"));

		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			article.setANS_NUM(ans_num);
			article.setANS_SUBJECT(request.getParameter("ANS_SUBJECT"));
			article.setANS_CONTENT(request.getParameter("ANS_CONTENT")); 
			isModifySuccess = ansboardModifyProService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('등록성공');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ansActionForward();
				forward.setRedirect(true);
				forward.setPath("ansboardDetail.ansbo?ans_num="+article.getANS_NUM()); 
			}

		}

		return forward;
	}

}