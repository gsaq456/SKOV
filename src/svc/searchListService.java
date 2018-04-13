package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.catalina.connector.Request;

import dao.ProductDAO;
import dao.SearchDAO;
import vo.BokhapBean;
import vo.JisungBean;
import vo.ProductBean;
import vo.TroubleBean;

public class searchListService {
	
	public int getListCount(String type, String searchid) throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		SearchDAO searchDAO = SearchDAO.getInstance();
		searchDAO.setConnection(con);
		listCount = searchDAO.selectListCount(type,searchid);
		close(con);
		return listCount;
		
	}
	
		public ArrayList<ProductBean> psearchList(int page, String type, String searchid) throws Exception{
		
		Connection con = getConnection();
		SearchDAO searchDAO = SearchDAO.getInstance();
		searchDAO.setConnection(con);
		ArrayList<ProductBean> psearchList = searchDAO.searchSearchList(page,type,searchid);
		close(con);
		return psearchList;
		}
	




}
