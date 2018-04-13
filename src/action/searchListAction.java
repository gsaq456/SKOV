package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.searchListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class searchListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String searchid = request.getParameter("searchid");
		String select = request.getParameter("select");
		ActionForward forward= new ActionForward();
		PageInfo pageInfo = new PageInfo();
		searchListService SearchListService = new searchListService();
		
		int page = 1;
		
		int listCount = SearchListService.getListCount(select,searchid); //총 리스트 수를 받아옴.
		 //리스트를 받아옴.
		//총 페이지 수.
   		 //0.95를 더해서 올림 처리.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startproduct = page;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	   

   		
		
   		pageInfo.setListCount(listCount);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startproduct);	
		request.setAttribute("pageInfo", pageInfo);
		
		
		searchListService searchListService = new searchListService();
		
		System.out.println("1번" + searchid);
		System.out.println("2번" + select);
		
		if(searchid.equals("")) {
			if(select.equals("건성")) {
				forward.setPath("/gunsungList.pr");
			}else if(select.equals("지성")) {
				forward.setPath("/jisungList.pr");
			}else if(select.equals("복합성")) {
				forward.setPath("/bokhapList.pr");
			}else if(select.equals("트러블")) {
				forward.setPath("/troubleList.pr");
			}
		}else {
			ArrayList<ProductBean> psearchList = searchListService.psearchList(page,select,searchid);
			request.setAttribute("psearchList", psearchList);
			forward.setPath("/main_View.jsp?main_page=psearchList");
		}
		
		
		
		return forward;
	}

}
