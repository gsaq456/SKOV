package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.JisungBean;
import vo.ProductBean;
import vo.ReplyBean;

public class JisungDetailService {
	
	public JisungBean JisungDetail(int product_num) throws Exception{
		// TODO Auto-generated method stub
		
		JisungBean jisungDetail = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
	
		jisungDetail = productDAO.JisungDetail(product_num);
		System.out.println(jisungDetail.getProduct_num());
		System.out.println(jisungDetail.getProduct_id());
		
		
		close(con);
		return jisungDetail;
		
	}
public ArrayList<ReplyBean> getReply(ProductBean productBean) throws Exception{
		
		ArrayList<ReplyBean> replyList = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		replyList = productDAO.selectReplylist(productBean);
		close(con);
		return replyList;
		
	}

}
