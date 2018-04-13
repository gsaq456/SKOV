package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import svc.BoardReplyWriteProService;

import vo.ActionForward;

import vo.ReplyBean;


public class BoardReplyWriteProAction implements Action{


	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
				
		ActionForward forward=null;
		ReplyBean replyBean = null;
		HttpSession session = request.getSession();
		
		
		int FB_num= Integer.parseInt(request.getParameter("FB_num"));
		String page = request.getParameter("page");
		
		replyBean = new ReplyBean();
		replyBean.setM_ID((String)session.getAttribute("login_id")); 
		replyBean.setFB_NUM(FB_num);
		replyBean.setFB_REPLY(request.getParameter("FB_REPLY"));
		
		
		BoardReplyWriteProService boardReplyWriteProService = new BoardReplyWriteProService();
	
		boolean isReplyWriteSuccess = boardReplyWriteProService.registReply(replyBean);

		if(!isReplyWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardDetail.bo?FB_num="+FB_num+"&page="+page);
		}

		return forward;
		
	}  	
	
}

