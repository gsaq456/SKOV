package ans_controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ans_action.ansAction;
import ans_action.ansBoardDeleteProAction;
import ans_action.ansBoardDetailAction;
import ans_action.ansBoardListAction;
import ans_action.ansBoardModifyFormAction;
import ans_action.ansBoardModifyProAction;
import ans_action.ansBoardWriteProAction;
import ans_vo.ansActionForward;

@WebServlet("*.ansbo")
public class ansBoardFrontController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ansActionForward forward=null;
		ansAction action=null;

		if(command.equals("/ansboardWriteForm.ansbo")){
			forward=new ansActionForward();
			forward.setPath("./main_View.jsp?main_page=ans_write");
		}else if(command.equals("/ansboardWritePro.ansbo")){
			action  = new ansBoardWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/ansboardList.ansbo")){
			action = new ansBoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/ansboardDetail.ansbo")){
			action = new ansBoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
		else if(command.equals("/ansboardModifyForm.ansbo")){
			action = new ansBoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/ansboardModifyPro.ansbo")){
			action = new ansBoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/ansboardDeleteForm.ansbo")){
			
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int ans_num=Integer.parseInt(request.getParameter("ans_num"));
			request.setAttribute("ans_num",ans_num);
			forward=new ansActionForward();
			forward.setPath("/ansboard/ans_board_delete.jsp");
		}
		else if(command.equals("/ansboardDeletePro.ansbo")){
			action = new ansBoardDeleteProAction();
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