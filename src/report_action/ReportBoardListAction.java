package report_action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report_svc.ReportBoardListService;
import report_vo.ReportActionForward;
import report_vo.ReportBoardBean;
import report_vo.ReportPageInfo;

 public class ReportBoardListAction implements ReportAction {
	 
	 public ReportActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<ReportBoardBean> articleList=new ArrayList<ReportBoardBean>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		ReportBoardListService boardListService = new ReportBoardListService();
		int listCount=boardListService.getListCount(); //�� ����Ʈ ���� �޾ƿ�.
		articleList = boardListService.getArticleList(page,limit); //����Ʈ�� �޾ƿ�.
		//�� ������ ��.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95�� ���ؼ� �ø� ó��.
   		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		ReportPageInfo pageInfo = new ReportPageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ReportActionForward forward= new ReportActionForward();
   		forward.setPath("./main_View.jsp?main_page=report");
   		return forward;
   		
	 }
	 
 }