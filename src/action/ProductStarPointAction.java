package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ProductStarPointService;
import vo.ActionForward;


public class ProductStarPointAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		HttpSession session = request.getSession();
		
		String login_id = (String)session.getAttribute("login_id");
		double starpoint= Double.parseDouble(request.getParameter("starpoint"));
		int product_num = Integer.parseInt(request.getParameter("productCode"));
		String productType = request.getParameter("productType");
		boolean insertSuccess = false;
		ProductStarPointService productStarPointService = new ProductStarPointService();
		
		productStarPointService.ProductStarPoint(login_id, starpoint, product_num, productType);
		insertSuccess = productStarPointService.starPointAVG(product_num,productType);
		
		ActionForward forward = new ActionForward();

	
		if(insertSuccess) {
			if(productType.equals("건성")) {
			forward.setPath("gunsung_product1.pr?product_num="+product_num);
			}
			else if(productType.equals("지성")) {
				forward.setPath("jisung_product1.pr?product_num="+product_num);
			}
			else if(productType.equals("복합성")) {
				forward.setPath("bokhap_product1.pr?product_num="+product_num);
			}
			else if(productType.equals("트러블")){
				forward.setPath("trouble_product1.pr?product_num="+product_num);
			}
		}
  		return forward;
	 }
}
