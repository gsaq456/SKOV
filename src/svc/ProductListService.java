package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.ProductBean;

public class ProductListService {
	
	public int getListCount(String type) throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		listCount = productDAO.selectListCount(type);
		close(con);
		return listCount;
		
	}

	public ArrayList<ProductBean> productList(int page, int limit) throws Exception{
		
		ArrayList<ProductBean> productList = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		productList = productDAO.selectArticleList(page,limit);
		close(con);
		return productList;
		
	}

}
