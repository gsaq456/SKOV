package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.JisungDetailService;
import vo.ActionForward;
import vo.JisungBean;
import vo.ProductBean;
import vo.ReplyBean;

public class JisungProductAction implements Action  {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		   	
		int product_num=Integer.parseInt(request.getParameter("product_num"));
		
		JisungDetailService jisungService = new JisungDetailService();
		JisungBean jisungDetail = jisungService.JisungDetail(product_num);
		ProductBean productBean = new ProductBean();
		
		String product_type = jisungDetail.getProduct_type();
		
		ArrayList<ReplyBean> replyList=new ArrayList<ReplyBean>();
		
		productBean.setProduct_num(product_num);
		productBean.setProduct_type(product_type);
		
		
		replyList = jisungService.getReply(productBean);
		
		ActionForward forward = new ActionForward();
		
		request.setAttribute("replyList", replyList);
		request.setAttribute("jisung", jisungDetail);
  		forward.setPath("/main_View.jsp?main_page=jisung1");
  		return forward;
	 }
}
