package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberListAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		
		ActionForward forward = null;
		/*
		  1. id가 비어있는지 확인
		  	- 비어있다면 로그인 페이지로 이동 함.
		  2. admin 인지 확인
		  	- admin 이 아니면, 관리자가 아니라는 alert 창 띄움.
		  	- 마찬가지로 로그인 페이지로 가도록 함.
		  3. admin 이라면
		  	- MemberListService 객체의 메소드를 호출함.
		  	- 호출결과를 받아서 memberlist.jsp로 이동함
		*/
		
		if(session.getAttribute("login_id") != null) {
			if(session.getAttribute("login_id").equals("admin")) {
				
				forward = new ActionForward();
				MemberListService memberListService = new MemberListService();
				ArrayList<MemberBean> memberList = memberListService.getMemberList();
				request.setAttribute("memberList", memberList);
				forward.setPath("./memberList_View.jsp");
				
				
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자로 로그인 하세요')");
				out.println("location.href = './memberLogin.me'");
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('즐!')");
			out.println("</script>");
			/*forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberLogin.me");*/
		}
		
		return forward;
	}
}
