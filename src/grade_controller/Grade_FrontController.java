package grade_controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grade_action.Grade_Action;
import grade_action.Grade_DeleteProAction;
import grade_action.Grade_DetailAction;
import grade_action.Grade_ListAction;
import grade_action.Grade_ModifyFormAction;
import grade_action.Grade_ModifyProAction;


import grade_action.Grade_WriteProAction;
import grade_vo.Grade_ActionForward;

@WebServlet("*.gradebo")
public class Grade_FrontController extends javax.servlet.http.HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		Grade_ActionForward forward=null;
		Grade_Action action=null;

		if(command.equals("/Grade_WriteForm.gradebo")){
			forward=new Grade_ActionForward();
			forward.setPath("./main_View.jsp?main_page=grade_write");
		}
		else if(command.equals("/Grade_WritePro.gradebo")){
			
			action  = new Grade_WriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/Grade_List.gradebo")){
			action = new Grade_ListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/Grade_ModifyForm.gradebo")){
			action = new Grade_ModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/Grade_Detail.gradebo")){
			action = new Grade_DetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/Grade_ModifyPro.gradebo")){
			action = new Grade_ModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/Grade_DeleteForm.gradebo")){
			String nowPage = request.getParameter("page");
			int up_num=Integer.parseInt(request.getParameter("up_num"));
			request.setAttribute("page", nowPage);
			request.setAttribute("up_num", up_num);
			forward=new Grade_ActionForward();
			forward.setPath("/gradeboard/Grade_board_delete.jsp");
		}
		else if(command.equals("/Grade_DeletePro.gradebo")){
			action = new Grade_DeleteProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				}		
			}
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}