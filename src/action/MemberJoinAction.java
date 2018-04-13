package action;

import java.io.PrintWriter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import svc.MemberJoinService;
import vo.ActionForward;
import vo.MemberBean;


public class MemberJoinAction  implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//member ��ü���� service �޼ҵ� ȣ���ϸ鼭 member �Ѱ���
		MemberBean member = new MemberBean();
		boolean joinResult = false;

		
		member.setMember_id(request.getParameter("member_id"));
		member.setMember_pw(request.getParameter("member_pw"));
		member.setMember_name(request.getParameter("member_name"));
		member.setMember_age(Integer.parseInt(request.getParameter("member_age")));
		member.setMember_gender(request.getParameter("member_gender"));
		member.setMember_email(request.getParameter("member_email"));
		member.setMember_addr(request.getParameter("member_addr"));
		member.setMember_skin(request.getParameter("member_skin"));
		member.setMember_grade(request.getParameter("member_grade"));
		
		//member ��ü�� ��� ������ MemberJOinService �� �ѱ�
		
		MemberJoinService memberJoinService = new MemberJoinService();
		//DB�� insert ���� ���������� ó�� �ƴٸ�
		//memberJoinService.joinMember(member)�޼ҵ��� ȣ�� ����� true �� �Ѿ��.
		//�翬�� insert ���� ����� ó�� �ȵǸ� false�� �Ѿ��.
		
		joinResult = memberJoinService.joinMember(member);
		
		ActionForward forward = null;
		
		if(joinResult == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ȸ������ ����')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ȸ������ ����')");
			out.println("window.close()");
			out.println("</script>");
		}
		
		
		
		return forward;
	}
	
}
