package ans_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ans_svc.ansBoardDetailService;
import ans_vo.ansActionForward;
import ans_vo.ansBoardBean;

public class ansBoardModifyFormAction implements ansAction {
	
	 public ansActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ansActionForward forward = new ansActionForward();
			int ans_num=Integer.parseInt(request.getParameter("ans_num"));
			ansBoardDetailService boardDetailService
			
			= new ansBoardDetailService();	
		   	ansBoardBean article =boardDetailService.getArticle(ans_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("/main_View.jsp?main_page=ans_modify");
	   		return forward;
	   		
	 }
	 
}