package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.JisungBean;

public class jisungListService {
	
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

	public ArrayList<JisungBean> jisungList(int page, int limit) throws Exception{
		
		ArrayList<JisungBean> jisungList = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		jisungList = productDAO.selectArticleList2(page,limit);
		close(con);
		return jisungList;
		
	}

}

