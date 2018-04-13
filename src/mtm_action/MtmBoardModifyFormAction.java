package mtm_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtm_svc.MtmBoardDetailService;
import mtm_vo.MtmActionForward;
import mtm_vo.MtmBoardBean;

public class MtmBoardModifyFormAction implements MtmAction {
	
	 public MtmActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	MtmActionForward forward = new MtmActionForward();
			int MTM_num=Integer.parseInt(request.getParameter("MTM_num"));
			MtmBoardDetailService boardDetailService
			= new MtmBoardDetailService();	
		   	MtmBoardBean article =boardDetailService.getArticle(MTM_num);
		   	request.setAttribute("article", article);
	   		forward.setPath("//main_View.jsp?main_page=mtm_modify");
	   		return forward;
	   		
	 }
	 
}