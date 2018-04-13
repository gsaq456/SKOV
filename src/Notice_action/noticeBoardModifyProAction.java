package Notice_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Notice_svc.noticeBoardModifyProService;
import Notice_vo.noticeActionForward;
import Notice_vo.noticeBoardBean;

public class noticeBoardModifyProAction implements noticeAction {

	public noticeActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		noticeActionForward forward = null;
		boolean isModifySuccess = false;
		int notice_num=Integer.parseInt(request.getParameter("NOTICE_NUM"));
		noticeBoardBean article=new noticeBoardBean();
		noticeBoardModifyProService noticeboardModifyProService = new noticeBoardModifyProService();
		boolean isRightUser=noticeboardModifyProService.isArticleWriter(notice_num, request.getParameter("NOTICE_PASS"));

		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('등록ok.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			article.setNOTICE_NUM(notice_num);
			article.setNOTICE_SUBJECT(request.getParameter("NOTICE_SUBJECT"));
			article.setNOTICE_CONTENT(request.getParameter("NOTICE_CONTENT")); 
			isModifySuccess = noticeboardModifyProService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('등록실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new noticeActionForward();
				forward.setRedirect(true);
				forward.setPath("noticeboardDetail.noticebo?notice_num="+article.getNOTICE_NUM()); 
			}

		}

		return forward;
	}

}