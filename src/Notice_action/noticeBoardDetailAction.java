package Notice_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Notice_svc.noticeBoardDetailService;
import Notice_vo.noticeActionForward;
import Notice_vo.noticeBoardBean;

 public class noticeBoardDetailAction implements noticeAction {
	 
	 public noticeActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int notice_num=Integer.parseInt(request.getParameter("notice_num"));
		String page = request.getParameter("page");
		noticeBoardDetailService boardDetailService = new noticeBoardDetailService();
		noticeBoardBean article = boardDetailService.getArticle(notice_num);
		noticeActionForward forward = new noticeActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/main_View.jsp?main_page=notice_write_view");
   		return forward;

	 }
	 
}