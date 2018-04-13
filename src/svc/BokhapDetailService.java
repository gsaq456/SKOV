package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.BokhapBean;
import vo.ProductBean;
import vo.ReplyBean;

public class BokhapDetailService {
	
	public BokhapBean BokhapDetail(int product_num) throws Exception{
		// TODO Auto-generated method stub
		
		BokhapBean BokhapDetail = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
	
		BokhapDetail = productDAO.BokhapDetail(product_num);
		
		
		
		close(con);
		return BokhapDetail;
		
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
