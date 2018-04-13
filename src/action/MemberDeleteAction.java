package action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberDeleteService;

import vo.ActionForward;

public class MemberDeleteAction implements Action{

public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		
		
		ActionForward forward = null;
		String m_id = (String)session.getAttribute("login_id");
		
		if(m_id != null) {
			if(m_id.equals("admin")) {
				
				String deleteId = request.getParameter("m_id");
				MemberDeleteService memberDeleteService = new MemberDeleteService();
				boolean deleteResult = memberDeleteService.deleteMember(deleteId);
				
				if(deleteResult){
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("./memberListAction.me");
				}else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					
					out.println("<script>");
					out.println("alert('회원정보 삭제 실패요')");
					out.println("location.href = './memverLogin.me'");
					out.println("</script>");
				}
				
				
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자로 로그인 하세요')");
				out.println("location.href = './memverLogin.me'");
				out.println("</script>");
			}
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memverLogin.me");
		}
		
		return forward;
	}
	
}
