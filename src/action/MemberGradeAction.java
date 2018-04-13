package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberGradeService;
import vo.ActionForward;


public class MemberGradeAction implements Action{
	
		//�������̽��� �޼ҵ��� ��ü �κ��� �ۼ���.
		public ActionForward execute(HttpServletRequest request,
									HttpServletResponse response) throws Exception {
			
			HttpSession session = request.getSession(); //���� ��ü ����
			
			String member_id = (String)session.getAttribute("login_id");	//id�� ������
			ActionForward forward = null;
			response.setContentType("text/html; charset=UTF-8");
			boolean gruntResult = false; 
			
			
			
					
			if(member_id==null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('�����ڷ� �α����ϼ���.')");
				out.println("window.close()");
				out.println("</script>");
			}
			else if(member_id.equals("admin")) {
				forward = new ActionForward();
								
				String r_member_id = (String)session.getAttribute("select_id");
				String r_member_grade = request.getParameter("grade");
				
				MemberGradeService MemberGradeService = new MemberGradeService();
				
				gruntResult = MemberGradeService.getMember(r_member_id,r_member_grade);
				
				if(gruntResult == false) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('��޼��� ����')");
					out.println("history.back()");
					out.println("</script>");
				}else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('��޼��� ����')");
					out.println("location.href ='./memberListAction.me';");
					out.println("</script>");
				}
				
			}
			return forward;
		}
}