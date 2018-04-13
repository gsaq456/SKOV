package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberGradeService;
import vo.ActionForward;


public class MemberGradeAction implements Action{
	
		//인터페이스의 메소드의 실체 부분을 작성함.
		public ActionForward execute(HttpServletRequest request,
									HttpServletResponse response) throws Exception {
			
			HttpSession session = request.getSession(); //세션 객체 생성
			
			String member_id = (String)session.getAttribute("login_id");	//id를 가져옴
			ActionForward forward = null;
			response.setContentType("text/html; charset=UTF-8");
			boolean gruntResult = false; 
			
			
			
					
			if(member_id==null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자로 로그인하세요.')");
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
					out.println("alert('등급수정 실패')");
					out.println("history.back()");
					out.println("</script>");
				}else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('등급수정 성공')");
					out.println("location.href ='./memberListAction.me';");
					out.println("</script>");
				}
				
			}
			return forward;
		}
}