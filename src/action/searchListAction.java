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
		
		int listCount = SearchListService.getListCount(select,searchid); //�� ����Ʈ ���� �޾ƿ�.
		 //����Ʈ�� �޾ƿ�.
		//�� ������ ��.
   		 //0.95�� ���ؼ� �ø� ó��.
   		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
   		int startproduct = page;
   		//���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
   	   

   		
		
   		pageInfo.setListCount(listCount);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startproduct);	
		request.setAttribute("pageInfo", pageInfo);
		
		
		searchListService searchListService = new searchListService();
		
		System.out.println("1��" + searchid);
		System.out.println("2��" + select);
		
		if(searchid.equals("")) {
			if(select.equals("�Ǽ�")) {
				forward.setPath("/gunsungList.pr");
			}else if(select.equals("����")) {
				forward.setPath("/jisungList.pr");
			}else if(select.equals("���ռ�")) {
				forward.setPath("/bokhapList.pr");
			}else if(select.equals("Ʈ����")) {
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
