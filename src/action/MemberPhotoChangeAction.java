package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.java.swing.plaf.windows.resources.windows;

import svc.MemberPhotoChangeService;
import vo.ActionForward;

import vo.MemberBean;


public class MemberPhotoChangeAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		HttpSession session = request.getSession();
		String login_id = (String)session.getAttribute("login_id");
			
		System.out.println("����� ����");
		MemberPhotoChangeService memberPhotoChangeService = new MemberPhotoChangeService();
		System.out.println("���� ��ü ��������");
		MemberBean changeMemberPhoto = memberPhotoChangeService.getMemberPhoto(login_id);
		System.out.println("���� ����");
		System.out.println("�� ����? " + changeMemberPhoto);
		
		ActionForward forward = new ActionForward();
		
		session.setAttribute("member_info", changeMemberPhoto);
	   
	   	forward.setPath("memberViewAction.me?m_id="+session.getAttribute("login_id"));
   		
   		return forward;

	 }
}
