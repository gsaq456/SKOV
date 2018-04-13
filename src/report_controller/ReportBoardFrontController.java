package report_controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report_action.ReportAction;
import report_action.ReportBoardDeleteProAction;
import report_action.ReportBoardDetailAction;
import report_action.ReportBoardListAction;
import report_action.ReportBoardModifyFormAction;
import report_action.ReportBoardModifyProAction;
import report_action.ReportBoardWriteProAction;
import report_vo.ReportActionForward;

@WebServlet("*.reportbo")
public class ReportBoardFrontController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ReportActionForward forward=null;
		ReportAction action=null;

		if(command.equals("/reportboardWriteForm.reportbo")){
			forward=new ReportActionForward();
			forward.setPath("./main_View.jsp?main_page=report_write");
		}else if(command.equals("/reportboardWritePro.reportbo")){
			action  = new ReportBoardWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reportboardList.reportbo")){
			action = new ReportBoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/reportboardDetail.reportbo")){
			action = new ReportBoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/reportboardModifyForm.reportbo")){
			action = new ReportBoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/reportboardModifyPro.reportbo")){
			action = new ReportBoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/reportboardDeleteForm.reportbo")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int REPORT_num=Integer.parseInt(request.getParameter("REPORT_num"));
			request.setAttribute("REPORT_num",REPORT_num);
			forward=new ReportActionForward();
			forward.setPath("/reportboard/report_board_delete.jsp");
		}
		else if(command.equals("/reportboardDeletePro.reportbo")){
			action = new ReportBoardDeleteProAction();
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