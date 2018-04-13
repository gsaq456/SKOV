package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLogoutService;
import vo.ActionForward;


public class MemberLogoutAction implements Action{


@SuppressWarnings("deprecation")
public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//member ��ü ���ٰ� Ŭ���̾�Ʈ�� �Է��� id,password �� ��������.
		
		
		HttpSession session = request.getSession();
				
		//member ��ü�� Ŭ���̾�Ʈ�� �Է��� id pw,�� ����
		// id pw �� �α������������� Ŭ���̾�Ʈ�� �Է��� id, pw
		session.removeValue("login_id");
		String login_id = (String)session.getAttribute("login_id");
		
		//memberLoginService Ŭ������ id pw ���� �Ѱ���
		//memberLoginService Ŭ�����κ��� �α��� ���� ,���� ���θ� ���Ϲ���
		
		MemberLogoutService memberLogoutService = new MemberLogoutService();
		//MemberLoginService Ŭ������ login �޼ҵ带 ȣ���Ͽ� ȣ�� ��� ����
		//loginResult�� ����.   �Ű����� member ��ü.
		
		boolean logoutResult = memberLogoutService.logout(login_id);
		ActionForward forward = null;
		
		if (logoutResult) {
			forward = new ActionForward();
						
			forward.setRedirect(true);
			forward.setPath("./main.me");
		}else {
			//loginResult = false �� 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�α׾ƿ� ����')");
			out.println("location.href ='./main.me';");
			out.println("</script>");
		}
		
		return forward;
		
	}
}
