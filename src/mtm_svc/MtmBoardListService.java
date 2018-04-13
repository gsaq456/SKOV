package mtm_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import mtm_dao.MtmBoardDAO;
import mtm_vo.MtmBoardBean;

public class MtmBoardListService {

	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<MtmBoardBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<MtmBoardBean> articleList = null;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
		boardDAO.setConnection(con);
		articleList = boardDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
