package Notice_action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Notice_svc.noticeBoardListService;
import Notice_vo.noticeActionForward;
import Notice_vo.noticeBoardBean;
import Notice_vo.noticePageInfo;

 public class noticeBoardListAction implements noticeAction {
	 
	 public noticeActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<noticeBoardBean> articleList=new ArrayList<noticeBoardBean>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		noticeBoardListService noticeboardListService = new noticeBoardListService();
		int listCount= noticeboardListService.getListCount(); //�� ����Ʈ ���� �޾ƿ�.
		articleList = noticeboardListService.getArticleList(page,limit); //����Ʈ�� �޾ƿ�.
		//�� ������ ��.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95�� ���ؼ� �ø� ó��.
   		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		noticePageInfo pageInfo = new noticePageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		System.out.println("아티클" + articleList);
		System.out.println("페이지 인포 " + pageInfo);
		noticeActionForward forward= new noticeActionForward();
   		forward.setPath("./main_View.jsp?main_page=notice");
   		return forward;
   		
	 }
	 
 }