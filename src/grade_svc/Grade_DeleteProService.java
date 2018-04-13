package grade_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import grade_dao.Grade_DAO;

public class Grade_DeleteProService {

	public boolean isArticleWriter(int up_num, String up_pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		Grade_DAO GradeDAO = Grade_DAO.getInstance();
		GradeDAO.setConnection(con);
		isArticleWriter = GradeDAO.isArticleBoardWriter(up_num, up_pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean removeArticle(int up_num) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		Grade_DAO GradeDAO = Grade_DAO.getInstance();
		GradeDAO.setConnection(con);
		int deleteCount = GradeDAO.deleteArticle(up_num);
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}

}
