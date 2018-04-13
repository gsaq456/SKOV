package report_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import report_dao.ReportBoardDAO;
import report_vo.ReportBoardBean;

public class ReportBoardListService {

	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		ReportBoardDAO boardDAO = ReportBoardDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<ReportBoardBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<ReportBoardBean> articleList = null;
		Connection con = getConnection();
		ReportBoardDAO boardDAO = ReportBoardDAO.getInstance();
		boardDAO.setConnection(con);
		articleList = boardDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
