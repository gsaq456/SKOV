package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductDetailService;
import vo.ActionForward;
import vo.ProductBean;
import vo.ReplyBean;

public class ProductAction implements Action  {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
			
		int product_num=Integer.parseInt(request.getParameter("product_num"));
		
		
		ProductDetailService productService = new ProductDetailService();
		ProductBean productDetail = productService.ProductDetail(product_num);
		ProductBean productBean = new ProductBean();
		
		String product_type = productDetail.getProduct_type();
		
		ArrayList<ReplyBean> replyList=new ArrayList<ReplyBean>();
		
		productBean.setProduct_num(product_num);
		productBean.setProduct_type(product_type);
		
		
		replyList = productService.getReply(productBean);
		
		
		ActionForward forward = new ActionForward();
		
		request.setAttribute("replyList", replyList);
		request.setAttribute("product", productDetail);
  		forward.setPath("/main_View.jsp?main_page=gunsung1");
  		return forward;
	 }
}
