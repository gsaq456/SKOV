package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoModifyService;

import vo.ActionForward;



public class MemberInfoModifyAction  implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("모디파이 액션 왔다");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(); 
		
		boolean memberModifySuccess = false;
		
		String member_id = (String)session.getAttribute("login_id");
		String member_Newpw = request.getParameter("member_pw");
		
		String member_Newaddr = request.getParameter("member_addr");
		
		String member_Newskin = request.getParameter("member_skin");
		
		
		MemberInfoModifyService memberInfoModifyService = new MemberInfoModifyService();
		memberModifySuccess = memberInfoModifyService.modifyInfo(member_id,member_Newpw,member_Newaddr,member_Newskin);
		
		if(!memberModifySuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패');");
			out.println("history.back()");
			out.println("</script>");
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("memberViewAction.me?m_id="+session.getAttribute("login_id")); 
		}
   		
   		
		return forward;
	}
}
