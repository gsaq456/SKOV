package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.TroubleDetailService;
import vo.ActionForward;
import vo.ProductBean;
import vo.ReplyBean;
import vo.TroubleBean;

public class TroubleProductAction implements Action  {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		   	
		int product_num=Integer.parseInt(request.getParameter("product_num"));
		
		TroubleDetailService troubleService = new TroubleDetailService();
		TroubleBean TroubleDetail = troubleService.TroubleDetail(product_num);
		ProductBean productBean = new ProductBean();
		
		String product_type = TroubleDetail.getProduct_type();
		
		ArrayList<ReplyBean> replyList=new ArrayList<ReplyBean>();
		
		productBean.setProduct_num(product_num);
		productBean.setProduct_type(product_type);
		
		
		replyList = troubleService.getReply(productBean);
		
		
		ActionForward forward = new ActionForward();
		
		
		request.setAttribute("replyList", replyList);
		request.setAttribute("trouble", TroubleDetail);
		forward.setPath("/main_View.jsp?main_page=trouble1");
		return forward;
	 }
}
