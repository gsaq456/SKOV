package ans_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ans_svc.ansBoardDetailService;
import ans_vo.ansActionForward;
import ans_vo.ansBoardBean;

 public class ansBoardDetailAction implements ansAction {
	 
	 public ansActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int ans_num=Integer.parseInt(request.getParameter("ans_num"));
		String page = request.getParameter("page");
		ansBoardDetailService boardDetailService = new ansBoardDetailService();
		ansBoardBean article = boardDetailService.getArticle(ans_num);
		ansActionForward forward = new ansActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/main_View.jsp?main_page=ans_write_view");
   		return forward;

	 }
	 
}