package grade_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import grade_dao.Grade_DAO;
import grade_vo.Grade_Bean;

public class Graed_ListService {

	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		Grade_DAO GradeDAO = Grade_DAO.getInstance();
		GradeDAO.setConnection(con);
		listCount = GradeDAO.selectListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<Grade_Bean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<Grade_Bean> articleList = null;
		Connection con = getConnection();
		Grade_DAO GradeDAO = Grade_DAO.getInstance();
		GradeDAO.setConnection(con);
		articleList = GradeDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
		
	}

}
