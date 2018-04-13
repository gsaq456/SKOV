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
		
		//member 객체 에다가 클라이언트가 입력한 id,password 를 담으세요.
		
		
		HttpSession session = request.getSession();
				
		//member 객체에 클라이언트가 입력한 id pw,를 담음
		// id pw 는 로그인페이지에서 클라이언트가 입력한 id, pw
		session.removeValue("login_id");
		String login_id = (String)session.getAttribute("login_id");
		
		//memberLoginService 클래스로 id pw 값을 넘겨줌
		//memberLoginService 클래스로부터 로그인 성공 ,실패 여부를 리턴받음
		
		MemberLogoutService memberLogoutService = new MemberLogoutService();
		//MemberLoginService 클래스의 login 메소드를 호출하여 호출 결과 값을
		//loginResult에 받음.   매개값은 member 객체.
		
		boolean logoutResult = memberLogoutService.logout(login_id);
		ActionForward forward = null;
		
		if (logoutResult) {
			forward = new ActionForward();
						
			forward.setRedirect(true);
			forward.setPath("./main.me");
		}else {
			//loginResult = false 면 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그아웃 실패')");
			out.println("location.href ='./main.me';");
			out.println("</script>");
		}
		
		return forward;
		
	}
}
