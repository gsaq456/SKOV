package grade_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grade_svc.Grade_DetailService;
import grade_vo.Grade_ActionForward;
import grade_vo.Grade_Bean;

public class Grade_ModifyFormAction implements Grade_Action {
	
	 public Grade_ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	Grade_ActionForward forward = new Grade_ActionForward();
			int up_num=Integer.parseInt(request.getParameter("up_num"));
			Grade_DetailService Grade_DetailService
			= new Grade_DetailService();	
		   	Grade_Bean article =Grade_DetailService.getArticle(up_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/main_View.jsp?main_page=grade_modify");
	   		return forward;
	   		
	 }
	 
}