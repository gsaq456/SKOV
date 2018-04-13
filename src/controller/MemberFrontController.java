package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.Action;
import mtm_action.MtmAction;
import action.BoardListAction;
import action.MemberDeleteAction;
import action.MemberGradeAction;
import action.MemberInfoModifyAction;
import action.MemberInfoModifyForm;
import action.MemberJoinAction;
import action.MemberListAction;
import action.MemberLoginAction;
import action.MemberLogoutAction;
import action.MemberPhotoAction;
import action.MemberPhotoChangeAction;
import action.MemberViewAction;
import mtm_action.MtmBoardListAction;
import vo.ActionForward;
import mtm_vo.MtmActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberFrontController() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
		
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트의 요청으로 부터 전송된 페이지의 앞부분은 걸러내고 필요한 부분만 잘라내는 과정
		String RequestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String command = RequestURI.substring(ContextPath.length());
		
		
		
		
		ActionForward forward = null;
		MtmActionForward mtmforward = null;
		Action action = null;
		MtmAction mtmaction = null; 
		//if - else  if 문을 이용하여 클라이언트가 요청한 페이지 별로 해당 기능을 수행할 수 있도록 포워딩하는 부분
		
		//로그인 요청
		if(command.equals("/memberLogin.me")) {
			
			//ActionForward()에 대한 객체 생성
			forward = new ActionForward();
			//ActionForward()의 setRedirect 메소드 호출
			forward.setRedirect(true);
			//로그인 요청이 오면 loginForm.jsp 페이지로 리다이렉트 시킴
			forward.setPath("./loginForm.jsp");
		}
		else if(command.equals("/memberLogoutAction.me")) {
			
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request,response);
			}catch (Exception e) {
				System.out.println("로그아웃? " + e);
				e.printStackTrace();
			}
		}		
		else if(command.equals("/memberLoginAction.me")) {
			
			action = new MemberLoginAction();
			try {
				forward = action.execute(request,response);
			}catch (Exception e) {
				System.out.println("로그인액션? " + e);
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberJoin.me")) {
						
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./join_View.jsp");
		}
		else if(command.equals("/memberJoinAction.me")) {
			
			action = new MemberJoinAction();
			try {
				forward = action.execute(request,response);
			}catch (Exception e) {
				System.out.println("회원가입액션? " + e);
				e.printStackTrace();
			}
		}
		//유저 정보 수정폼
		else if(command.equals("/memberInfoModifyForm.me")){
			action = new MemberInfoModifyForm();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//유저 정보 수정액션
		else if(command.equals("/memberInfoModifyAction.me")){
			
			action = new MemberInfoModifyAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberListAction.me")) {
			
			action = new MemberListAction();
			try {
				forward = action.execute(request,response);
			}catch (Exception e) {
				System.out.println("리스트액션? " + e);
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberDeleteAction.me")) {
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				System.out.println("딜리트? " + e);
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberViewAction.me")) {
			action = new MemberViewAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				System.out.println("부ㅠ? " + e);
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberGradeAction.me")) {
			
			action = new MemberGradeAction();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				System.out.println("등급? " + e);
				e.printStackTrace();
			}
		}
		//유저 사진 등록
		else if(command.equals("/memberPhotoAction.me")) {
			
			action = new MemberPhotoAction();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				
				e.printStackTrace();
			}
		}
		//유저 사진 변경
		else if(command.equals("/memberPhotoChangeAction.me")) {
					
			action = new MemberPhotoChangeAction();
					
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
						
				e.printStackTrace();
			}
		}
		else if(command.equals("/free.me")) {
			
			action = new BoardListAction();
			
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/grade.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main_View.jsp?main_page=grade");
		}
		else if(command.equals("/report.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main_View.jsp?main_page=report");
		}
		else if(command.equals("/mantoman.me")) {
			
			
			mtmaction = new MtmBoardListAction();
			
			try{
				mtmforward= mtmaction.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/notice.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main_View.jsp?main_page=notice");
		}
		else if(command.equals("/main.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main_View.jsp?main_page=base");
		}
		else if(command.equals("/gunsung.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main_View.jsp?main_page=gunsung");
		}
		else if(command.equals("/jisung.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main_View.jsp?main_page=jisung");
		}
		else if(command.equals("/bokhap.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main_View.jsp?main_page=bokhap");
		}
		else if(command.equals("/trouble.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main_View.jsp?main_page=trouble");
		}
		
		if (forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else if(forward.getPath() != null){
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	
}
