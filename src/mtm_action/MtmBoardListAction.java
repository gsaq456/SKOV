package mtm_action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtm_svc.MtmBoardListService;
import mtm_vo.MtmActionForward;
import mtm_vo.MtmBoardBean;
import mtm_vo.MtmPageInfo;

 public class MtmBoardListAction implements MtmAction {
	 
	 public MtmActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 
		ArrayList<MtmBoardBean> articleList=new ArrayList<MtmBoardBean>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		MtmBoardListService boardListService = new MtmBoardListService();
		int listCount=boardListService.getListCount(); //총 리스트 수를 받아옴.
		articleList = boardListService.getArticleList(page,limit); //리스트를 받아옴.
		//총 페이지 수.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		MtmPageInfo pageInfo = new MtmPageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);

		
		MtmActionForward forward= new MtmActionForward();
		

		
		forward.setPath("./main_View.jsp?main_page=mantoman");
   		
		return forward;
   		
	 }
	 
 }