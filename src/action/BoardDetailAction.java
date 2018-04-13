package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;
import vo.ReplyBean;

 public class BoardDetailAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		
		String page = request.getParameter("page");
		
		int FB_num=Integer.parseInt(request.getParameter("FB_num"));
		
	
		
		BoardDetailService boardDetailService = new BoardDetailService();
		ArrayList<ReplyBean> replyList=new ArrayList<ReplyBean>();
		BoardBean article = boardDetailService.getArticle(FB_num);
		replyList = boardDetailService.getReply(FB_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
	   	request.setAttribute("replyList", replyList);
	   	
   		forward.setPath("/main_View.jsp?main_page=free_write_view");
   		return forward;

	 }
	 
}