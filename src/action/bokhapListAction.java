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
		String type = "���ռ�";
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		BokhapListService BokhapListService = new BokhapListService();
		int listCount=BokhapListService.getListCount(type); //�� ����Ʈ ���� �޾ƿ�.
		bokhapList = BokhapListService.bokhapList(page,limit); //����Ʈ�� �޾ƿ�.
		//�� ������ ��.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95�� ���ؼ� �ø� ó��.
   		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
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

