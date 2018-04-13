package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;


import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.ProductDAO;
import vo.ProductBean;
import vo.ReplyBean;

public class ProductDetailService {
	
	public ProductBean ProductDetail(int product_num) throws Exception{
		// TODO Auto-generated method stub
		
		ProductBean productDetail = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
	
		productDetail = productDAO.ProductDetail(product_num);
		
		
		
		close(con);
		return productDetail;
		
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
