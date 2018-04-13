package ans_action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ans_svc.ansBoardListService;
import ans_vo.ansActionForward;
import ans_vo.ansBoardBean;
import ans_vo.ansPageInfo;

 public class ansBoardListAction implements ansAction {
	 
	 public ansActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<ansBoardBean> articleList=new ArrayList<ansBoardBean>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		ansBoardListService ansboardListService = new ansBoardListService();
		int listCount= ansboardListService.getListCount(); //�� ����Ʈ ���� �޾ƿ�.
		articleList = ansboardListService.getArticleList(page,limit); //����Ʈ�� �޾ƿ�.
		//�� ������ ��.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95�� ���ؼ� �ø� ó��.
   		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		ansPageInfo pageInfo = new ansPageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		System.out.println("아티클" + articleList);
		System.out.println("페이지 인포 " + pageInfo);
		ansActionForward forward= new ansActionForward();
   		forward.setPath("./main_View.jsp?main_page=ans");
   		return forward;
   		
	 }
	 
 }