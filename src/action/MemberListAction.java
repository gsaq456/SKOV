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
		  1. id�� ����ִ��� Ȯ��
		  	- ����ִٸ� �α��� �������� �̵� ��.
		  2. admin ���� Ȯ��
		  	- admin �� �ƴϸ�, �����ڰ� �ƴ϶�� alert â ���.
		  	- ���������� �α��� �������� ������ ��.
		  3. admin �̶��
		  	- MemberListService ��ü�� �޼ҵ带 ȣ����.
		  	- ȣ������ �޾Ƽ� memberlist.jsp�� �̵���
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
				out.println("alert('�����ڷ� �α��� �ϼ���')");
				out.println("location.href = './memberLogin.me'");
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��!')");
			out.println("</script>");
			/*forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberLogin.me");*/
		}
		
		return forward;
	}
}
