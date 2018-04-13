package Notice_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Notice_svc.noticeBoardDetailService;
import Notice_vo.noticeActionForward;
import Notice_vo.noticeBoardBean;

public class noticeBoardModifyFormAction implements noticeAction {
	
	 public noticeActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	noticeActionForward forward = new noticeActionForward();
			int notice_num=Integer.parseInt(request.getParameter("notice_num"));
			noticeBoardDetailService boardDetailService
			= new noticeBoardDetailService();	
		   	noticeBoardBean article =boardDetailService.getArticle(notice_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/main_View.jsp?main_page=notice_modify");
	   		return forward;
	   		
	 }
	 
}