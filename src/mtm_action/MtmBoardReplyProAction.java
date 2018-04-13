package mtm_action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtm_svc.MtmBoardReplyProService;
import mtm_vo.MtmActionForward;
import mtm_vo.MtmBoardBean;

public class MtmBoardReplyProAction implements MtmAction {
	
	 public MtmActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 
		 	MtmActionForward forward = null;
		    String nowPage = request.getParameter("page");
		 	MtmBoardBean article = new MtmBoardBean();  		
		 	article.setMTM_NUM(Integer.parseInt(request.getParameter("MTM_NUM")));
		 	article.setMTM_NAME(request.getParameter("MTM_NAME"));
		 	article.setMTM_PASS(request.getParameter("MTM_PASS"));
		 	article.setMTM_SUBJECT(request.getParameter("MTM_SUBJECT"));
		 	article.setMTM_CONTENT(request.getParameter("MTM_CONTENT"));
		 	article.setMTM_RE_REF(Integer.parseInt(request.getParameter("MTM_RE_REF")));
		 	article.setMTM_RE_LEV(Integer.parseInt(request.getParameter("MTM_RE_LEV")));
		 	article.setMTM_RE_SEQ(Integer.parseInt(request.getParameter("MTM_RE_SEQ")));	   		
		 	MtmBoardReplyProService boardReplyProService = new MtmBoardReplyProService();
		 	boolean isReplySuccess = boardReplyProService.replyArticle(article);
		 	
	   		if(isReplySuccess){
	   			forward = new MtmActionForward();
	   			forward.setRedirect(true);
	   			forward.setPath("mtmboardList.mtmbo?page=" + nowPage);
	   		}
	   		else{
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('답장실패')");
	   			out.println("history.back()");
	   			out.println("</script>");
	   		}
	   		
	   		return forward;
	   		
	}  	
	 
}