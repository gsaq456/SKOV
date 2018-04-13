package action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import svc.MemberPhotoService;
import vo.ActionForward;

import vo.MemberBean;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class MemberPhotoAction implements Action  {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		ActionForward forward=null;
		MemberBean memberBean = null;
		String realFolder="";
		String saveFolder="/memberPhoto";
		int fileSize=40*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);   		
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		HttpSession session = request.getSession();
		
		memberBean = new MemberBean();
		memberBean.setMember_id((String)session.getAttribute("login_id"));
		memberBean.setMember_photo(
		multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		MemberPhotoService memberPhotoService = new MemberPhotoService();
		
		boolean changeSuccess = memberPhotoService.photoChange(memberBean);

		if(!changeSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("memberPhotoChangeAction.me");
		}

		return forward;
		
	}  	
}
