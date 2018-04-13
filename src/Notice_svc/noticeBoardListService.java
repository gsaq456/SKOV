package Notice_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import Notice_dao.noticeBoardDAO;
import Notice_vo.noticeBoardBean;

public class noticeBoardListService {

	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		noticeBoardDAO noticeboardDAO = noticeBoardDAO.getInstance();
		noticeboardDAO.setConnection(con);
		listCount = noticeboardDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<noticeBoardBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<noticeBoardBean> articleList = null;
		Connection con = getConnection();
		noticeBoardDAO noticeboardDAO = noticeBoardDAO.getInstance();
		noticeboardDAO.setConnection(con);
		articleList = noticeboardDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
