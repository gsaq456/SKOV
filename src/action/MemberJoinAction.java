package action;

import java.io.PrintWriter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import svc.MemberJoinService;
import vo.ActionForward;
import vo.MemberBean;


public class MemberJoinAction  implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//member 객체만들어서 service 메소드 호출하면서 member 넘겨줌
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
		
		//member 객체에 담긴 값들을 MemberJOinService 로 넘김
		
		MemberJoinService memberJoinService = new MemberJoinService();
		//DB의 insert 문이 정상적으로 처리 됐다면
		//memberJoinService.joinMember(member)메소드의 호출 결과는 true 로 넘어옴.
		//당연히 insert 문이 제대로 처리 안되면 false로 넘어옴.
		
		joinResult = memberJoinService.joinMember(member);
		
		ActionForward forward = null;
		
		if(joinResult == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 성공')");
			out.println("window.close()");
			out.println("</script>");
		}
		
		
		
		return forward;
	}
	
}
