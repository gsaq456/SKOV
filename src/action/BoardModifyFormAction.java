package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
			int FB_num=Integer.parseInt(request.getParameter("FB_num"));
			BoardDetailService boardDetailService = new BoardDetailService();	
		   	BoardBean article =boardDetailService.getArticle(FB_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/main_View.jsp?main_page=free_modify");
	   		return forward;
	   		
	 }
	 
}