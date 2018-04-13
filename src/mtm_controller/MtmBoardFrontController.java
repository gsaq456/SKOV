package mtm_controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtm_action.MtmAction;
import mtm_action.MtmBoardDeleteProAction;
import mtm_action.MtmBoardDetailAction;
import mtm_action.MtmBoardListAction;
import mtm_action.MtmBoardModifyFormAction;
import mtm_action.MtmBoardModifyProAction;
import mtm_action.MtmBoardReplyFormAction;
import mtm_action.MtmBoardReplyProAction;
import mtm_action.MtmBoardWriteProAction;
import mtm_vo.MtmActionForward;

@WebServlet("*.mtmbo")
public class MtmBoardFrontController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		MtmActionForward forward=null;
		MtmAction action=null;

		if(command.equals("/mtmboardWriteForm.mtmbo")){
			forward=new MtmActionForward();
			forward.setPath("./main_View.jsp?main_page=mtm_write");
		}else if(command.equals("/mtmboardWritePro.mtmbo")){
			action  = new MtmBoardWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/mtmboardList.mtmbo")){
			action = new MtmBoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/mtmboardDetail.mtmbo")){
			action = new MtmBoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/mtmboardReplyForm.mtmbo")){
			action = new MtmBoardReplyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/mtmboardReplyPro.mtmbo")){
			action = new MtmBoardReplyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/mtmboardModifyForm.mtmbo")){
			action = new MtmBoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/mtmboardModifyPro.mtmbo")){
			action = new MtmBoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/mtmboardDeleteForm.mtmbo")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int MTM_num=Integer.parseInt(request.getParameter("MTM_num"));
			request.setAttribute("MTM_num",MTM_num);
			forward=new MtmActionForward();
			forward.setPath("/mtmboard/mtm_board_delete.jsp");
		}
		else if(command.equals("/mtmboardDeletePro.mtmbo")){
			action = new MtmBoardDeleteProAction();
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