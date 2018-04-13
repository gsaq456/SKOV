package grade_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grade_svc.Grade_DetailService;
import grade_vo.Grade_ActionForward;
import grade_vo.Grade_Bean;

 public class Grade_DetailAction implements Grade_Action {
	 
	 public Grade_ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int up_num=Integer.parseInt(request.getParameter("up_num"));
		
		String page = request.getParameter("page");
		Grade_DetailService boardDetailService = new Grade_DetailService();
		Grade_Bean article = boardDetailService.getArticle(up_num);
		Grade_ActionForward forward = new Grade_ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/main_View.jsp?main_page=grade_write_view");
   		return forward;

	 }
	 
}