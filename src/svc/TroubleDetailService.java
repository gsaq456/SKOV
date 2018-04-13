package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.ProductBean;
import vo.ReplyBean;
import vo.TroubleBean;

public class TroubleDetailService {
	
	public TroubleBean TroubleDetail(int product_num) throws Exception{
		// TODO Auto-generated method stub
		
		TroubleBean TroubleDetail = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
	
		TroubleDetail = productDAO.TroubleDetail(product_num);
		System.out.println(TroubleDetail.getProduct_num());
		System.out.println(TroubleDetail.getProduct_id());
		
		
		close(con);
		return TroubleDetail;
		
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


