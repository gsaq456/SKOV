package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BokhapDetailService;
import vo.ActionForward;
import vo.BokhapBean;
import vo.ProductBean;
import vo.ReplyBean;

public class BokhapProductAction implements Action  {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		   	
		int product_num=Integer.parseInt(request.getParameter("product_num"));
		
		BokhapDetailService bokhapService = new BokhapDetailService();
		BokhapBean BokhapDetail = bokhapService.BokhapDetail(product_num);
		ProductBean productBean = new ProductBean();
		
		String product_type = BokhapDetail.getProduct_type();
		
		ArrayList<ReplyBean> replyList=new ArrayList<ReplyBean>();
		
		productBean.setProduct_num(product_num);
		productBean.setProduct_type(product_type);
		
		
		replyList = bokhapService.getReply(productBean);
		
		ActionForward forward = new ActionForward();
		
		
		
		request.setAttribute("replyList", replyList);
		request.setAttribute("bokhap", BokhapDetail);
 		forward.setPath("/main_View.jsp?main_page=bokhap1");
 		return forward;
	 }
}
