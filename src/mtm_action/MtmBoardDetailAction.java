package mtm_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtm_svc.MtmBoardDetailService;
import mtm_vo.MtmActionForward;
import mtm_vo.MtmBoardBean;

 public class MtmBoardDetailAction implements MtmAction {
	 
	 public MtmActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int MTM_num=Integer.parseInt(request.getParameter("MTM_num"));
		String page = request.getParameter("page");
		MtmBoardDetailService boardDetailService = new MtmBoardDetailService();
		MtmBoardBean article = boardDetailService.getArticle(MTM_num);
		MtmActionForward forward = new MtmActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/main_View.jsp?main_page=mtm_write_view");
   		return forward;

	 }
	 
}