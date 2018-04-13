package grade_action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import grade_svc.Graed_ListService;
import grade_vo.Grade_ActionForward;
import grade_vo.Grade_Bean;
import grade_vo.Grade_PageInfo;

 public class Grade_ListAction implements Grade_Action {
	 
	 public Grade_ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<Grade_Bean> articleList=new ArrayList<Grade_Bean>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		Graed_ListService Grade_ListService = new Graed_ListService();
		int listCount=Grade_ListService.getListCount(); 
		articleList = Grade_ListService.getArticleList(page,limit); 
   		int maxPage=(int)((double)listCount/limit+0.95); 
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;
                             
   		Grade_PageInfo pageInfo = new Grade_PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		Grade_ActionForward forward= new Grade_ActionForward();
		
   		forward.setPath("./main_View.jsp?main_page=grade");
   		return forward;
   		
	 }
	 
 }