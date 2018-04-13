package action;

import java.io.PrintWriter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import svc.MemberLoginService;
import vo.ActionForward;
import vo.MemberBean;

//로그인 요청을 처리하기 위한 LoginAction 클래스
public class MemberLoginAction implements Action{
	
	//Action 인터페이스의 execute 실체 메소드 작성
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//member 객체 에다가 클라이언트가 입력한 id,password 를 담으세요.
		
		String member_id = request.getParameter("member_id");
		String member_pw = request.getParameter("member_pw");
		MemberBean member = new MemberBean();
		HttpSession session = request.getSession();
		
		//member 객체에 클라이언트가 입력한 id pw,를 담음
		// id pw 는 로그인페이지에서 클라이언트가 입력한 id, pw
		member.setMember_id(member_id);
		member.setMember_pw(member_pw);
		
		//memberLoginService 클래스로 id pw 값을 넘겨줌
		//memberLoginService 클래스로부터 로그인 성공 ,실패 여부를 리턴받음
		
		MemberLoginService memberLoginService = new MemberLoginService();
		//MemberLoginService 클래스의 login 메소드를 호출하여 호출 결과 값을
		//loginResult에 받음.   매개값은 member 객체.
		
		boolean loginResult = memberLoginService.login(member);
		
		
		ActionForward forward = null;
		
		if (loginResult) {
			forward = new ActionForward();
			//id가 존재하는 회원이면 해당 id를 session 영역에 저장
			session.setAttribute("login_id", member.getMember_id());
			MemberBean member_info = memberLoginService.selectInfo((String)session.getAttribute("login_id"));
			session.setAttribute("member_info", member_info);
			forward.setRedirect(true);
			forward.setPath("./main.me");
		}else {
			//loginResult = false 면 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패')");
			out.println("location.href ='./main.me';");
			out.println("</script>");
		}
		
		return forward;
		
	}
	
}
