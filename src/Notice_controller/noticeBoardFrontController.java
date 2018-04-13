package Notice_controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Notice_action.noticeAction;
import Notice_action.noticeBoardDeleteProAction;
import Notice_action.noticeBoardDetailAction;
import Notice_action.noticeBoardListAction;
import Notice_action.noticeBoardModifyFormAction;
import Notice_action.noticeBoardModifyProAction;

import Notice_action.noticeBoardWriteProAction;
import Notice_vo.noticeActionForward;

@WebServlet("*.noticebo")
public class noticeBoardFrontController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		noticeActionForward forward=null;
		noticeAction action=null;

		if(command.equals("/noticeboardWriteForm.noticebo")){
			forward=new noticeActionForward();
			forward.setPath("./main_View.jsp?main_page=notice_write");
		}else if(command.equals("/noticeboardWritePro.noticebo")){
			action  = new noticeBoardWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/noticeboardList.noticebo")){
			action = new noticeBoardListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/noticeboardDetail.noticebo")){
			action = new noticeBoardDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
		else if(command.equals("/noticeboardModifyForm.noticebo")){
			action = new noticeBoardModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/noticeboardModifyPro.noticebo")){
			action = new noticeBoardModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/noticeboardDeleteForm.noticebo")){
			
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int notice_num=Integer.parseInt(request.getParameter("notice_num"));
			request.setAttribute("notice_num",notice_num);
			forward=new noticeActionForward();
			forward.setPath("/noticeboard/Notice_board_delete.jsp");
		}
		else if(command.equals("/noticeboardDeletePro.noticebo")){
			action = new noticeBoardDeleteProAction();
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