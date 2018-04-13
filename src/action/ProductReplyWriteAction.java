package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BoardReplyWriteProService;
import svc.ProductReplyWriteService;
import vo.ActionForward;
import vo.ProductBean;
import vo.ReplyBean;

public class ProductReplyWriteAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		ActionForward forward=null;
		ReplyBean replyBean = null;
		ProductBean productBean = null;
		
		
		HttpSession session = request.getSession();
		String login_id = (String)session.getAttribute("login_id");
		int product_num = Integer.parseInt(request.getParameter("productCode"));
		String product_type = request.getParameter("productType");
		
		productBean = new ProductBean();
		replyBean = new ReplyBean();
		
		replyBean.setM_ID(login_id); 
		replyBean.setFB_REPLY(request.getParameter("pr_coment"));
		productBean.setProduct_num(product_num);
		productBean.setProduct_type(product_type);
		
		ProductReplyWriteService productReplyWriteService = new ProductReplyWriteService();
	
		boolean isReplyWriteSuccess = productReplyWriteService.registReply(replyBean,productBean);

		if(!isReplyWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			if(product_type.equals("건성")) {
			forward.setPath("gunsung_product1.pr?product_num="+product_num);
			}
			else if(product_type.equals("지성")) {
				forward.setPath("jisung_product1.pr?product_num="+product_num);
			}
			else if(product_type.equals("복합성")) {
				forward.setPath("bokhap_product1.pr?product_num="+product_num);
			}
			else if(product_type.equals("트러블")) {
				forward.setPath("trouble_product1.pr?product_num="+product_num);
			}
		}

		return forward;
		
	}  	
}
