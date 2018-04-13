package ans_svc;

import static ans_db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import ans_dao.ansBoardDAO;
import ans_vo.ansBoardBean;

public class ansBoardListService {

	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		ansBoardDAO ansboardDAO = ansBoardDAO.getInstance();
		ansboardDAO.setConnection(con);
		listCount = ansboardDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<ansBoardBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<ansBoardBean> articleList = null;
		Connection con = getConnection();
		ansBoardDAO ansboardDAO = ansBoardDAO.getInstance();
		ansboardDAO.setConnection(con);
		articleList = ansboardDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
