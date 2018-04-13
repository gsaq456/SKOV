package grade_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import grade_dao.Grade_DAO;
import grade_vo.Grade_Bean;
public class Grade_WriteProService {

	private static final Grade_Bean Grade_Bean = null;

	public boolean registArticle(Grade_Bean article) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		Grade_DAO GradeDAO = Grade_DAO.getInstance();
		GradeDAO.setConnection(con);
		int insertCount = GradeDAO.insertArticle(article);
		
		if(insertCount > 0){
			commit(con);
			isWriteSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isWriteSuccess;
		
	}

}
