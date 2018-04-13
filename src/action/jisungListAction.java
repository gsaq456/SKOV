package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.jisungListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.JisungBean;

public class jisungListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<JisungBean> jisungList=new ArrayList<JisungBean>();
	  	int page=1;
		int limit=4;
		String type = "지성";
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		jisungListService jisungListService = new jisungListService();
		
		int listCount=jisungListService.getListCount(type); //총 리스트 수를 받아옴.
		jisungList = jisungListService.jisungList(page,limit); //리스트를 받아옴.
		
		//총 페이지 수.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리.
   		
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	    int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("jisungList", jisungList);
		ActionForward forward= new ActionForward();
		System.out.println(jisungList);
   		forward.setPath("/main_View.jsp?main_page=jisungList");
   		return forward;
   		
	 }

}



