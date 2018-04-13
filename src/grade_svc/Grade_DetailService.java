package grade_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import grade_dao.Grade_DAO;
import grade_vo.Grade_Bean;

public class Grade_DetailService {

	public Grade_Bean getArticle(int up_num) throws Exception{
		// TODO Auto-generated method stub
		
		Grade_Bean article = null;
		Connection con = getConnection();
		Grade_DAO GradeDAO = Grade_DAO.getInstance();
		GradeDAO.setConnection(con);
		
		
		article = GradeDAO.selectArticle(up_num);
		close(con);
		return article;
		
	}

}
