package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.BokhapBean;

public class BokhapListService {
	
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

	public ArrayList<BokhapBean> bokhapList(int page, int limit) throws Exception{
		
		ArrayList<BokhapBean> bokhapList = null;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		bokhapList = productDAO.selectArticleList3(page,limit);
		close(con);
		return bokhapList;
		
	}

}

