package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BokhapListService;
import vo.ActionForward;
import vo.BokhapBean;
import vo.PageInfo;

public class bokhapListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<BokhapBean> bokhapList=new ArrayList<BokhapBean>();
	  	int page=1;
		int limit=4;
		String type = "복합성";
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		BokhapListService BokhapListService = new BokhapListService();
		int listCount=BokhapListService.getListCount(type); //총 리스트 수를 받아옴.
		bokhapList = BokhapListService.bokhapList(page,limit); //리스트를 받아옴.
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
		request.setAttribute("bokhapList", bokhapList);
		ActionForward forward= new ActionForward();
		System.out.println(bokhapList);
   		forward.setPath("/main_View.jsp?main_page=bokhapList");
   		return forward;
   		
	 }

}

