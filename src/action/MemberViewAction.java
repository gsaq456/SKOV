package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberViewService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberViewAction implements Action{
	
		//�������̽��� �޼ҵ��� ��ü �κ��� �ۼ���.
		public ActionForward execute(HttpServletRequest request,
									HttpServletResponse response) throws Exception {
			
			HttpSession session = request.getSession(); //���� ��ü ����
			String member_id = (String) session.getAttribute("login_id");	//id�� ������
			ActionForward forward = null;
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
					
			if(member_id==null) {
				out.println("<script>");
				out.println("alert('�α����ϼ���.')");
				out.println("window.close()");
				out.println("</script>");
			}
			else {
				forward = new ActionForward();
				String view_member_id = request.getParameter("m_id");
				MemberViewService memberViewService = new MemberViewService();
				MemberBean id = memberViewService.getMember(view_member_id);
				request.setAttribute("member", id);
				session.setAttribute("select_id",id.getMember_id());
				
				forward.setPath("./member_info.jsp");
				
			}
			
			
			return forward;
		}

}
