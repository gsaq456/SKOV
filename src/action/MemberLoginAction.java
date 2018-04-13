package action;

import java.io.PrintWriter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import svc.MemberLoginService;
import vo.ActionForward;
import vo.MemberBean;

//�α��� ��û�� ó���ϱ� ���� LoginAction Ŭ����
public class MemberLoginAction implements Action{
	
	//Action �������̽��� execute ��ü �޼ҵ� �ۼ�
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//member ��ü ���ٰ� Ŭ���̾�Ʈ�� �Է��� id,password �� ��������.
		
		String member_id = request.getParameter("member_id");
		String member_pw = request.getParameter("member_pw");
		MemberBean member = new MemberBean();
		HttpSession session = request.getSession();
		
		//member ��ü�� Ŭ���̾�Ʈ�� �Է��� id pw,�� ����
		// id pw �� �α������������� Ŭ���̾�Ʈ�� �Է��� id, pw
		member.setMember_id(member_id);
		member.setMember_pw(member_pw);
		
		//memberLoginService Ŭ������ id pw ���� �Ѱ���
		//memberLoginService Ŭ�����κ��� �α��� ���� ,���� ���θ� ���Ϲ���
		
		MemberLoginService memberLoginService = new MemberLoginService();
		//MemberLoginService Ŭ������ login �޼ҵ带 ȣ���Ͽ� ȣ�� ��� ����
		//loginResult�� ����.   �Ű����� member ��ü.
		
		boolean loginResult = memberLoginService.login(member);
		
		
		ActionForward forward = null;
		
		if (loginResult) {
			forward = new ActionForward();
			//id�� �����ϴ� ȸ���̸� �ش� id�� session ������ ����
			session.setAttribute("login_id", member.getMember_id());
			MemberBean member_info = memberLoginService.selectInfo((String)session.getAttribute("login_id"));
			session.setAttribute("member_info", member_info);
			forward.setRedirect(true);
			forward.setPath("./main.me");
		}else {
			//loginResult = false �� 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�α��� ����')");
			out.println("location.href ='./main.me';");
			out.println("</script>");
		}
		
		return forward;
		
	}
	
}
