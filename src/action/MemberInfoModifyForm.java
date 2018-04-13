package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import svc.MemberViewService;
import vo.ActionForward;

import vo.MemberBean;

public class MemberInfoModifyForm implements Action{
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(); 
		String member_id = (String)session.getAttribute("login_id");
		
		MemberViewService memberViewService = new MemberViewService();
		MemberBean id = memberViewService.getMember(member_id);
		
		request.setAttribute("member", id);
		
   		forward.setPath("/member_info_modify.jsp");
   		
		return forward;
	   		
	 }
}
