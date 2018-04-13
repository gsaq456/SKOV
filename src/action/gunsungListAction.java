package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import svc.ProductListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class gunsungListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<ProductBean> productList=new ArrayList<ProductBean>();
	  	int page=1;
		int limit=4;
		String type = "�Ǽ�";
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		ProductListService productListService = new ProductListService();
		int listCount=productListService.getListCount(type); //�� ����Ʈ ���� �޾ƿ�.
		productList = productListService.productList(page,limit); //����Ʈ�� �޾ƿ�.
		//�� ������ ��.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95�� ���ؼ� �ø� ó��.
   		//���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		System.out.println("����" + startPage);
   		System.out.println("��" + endPage);
   		
   		
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("productList", productList);
		ActionForward forward= new ActionForward();
		
   		forward.setPath("/main_View.jsp?main_page=gunsungList");
   		return forward;
   		
	 }

}
