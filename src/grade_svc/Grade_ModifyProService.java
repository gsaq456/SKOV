package grade_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import grade_dao.Grade_DAO;
import grade_vo.Grade_Bean;

public class Grade_ModifyProService {

	public boolean isArticleWriter(int up_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		Grade_DAO GradeDAO = Grade_DAO.getInstance();
		GradeDAO.setConnection(con);
		isArticleWriter = GradeDAO.isArticleBoardWriter(up_num, pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(Grade_Bean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		Grade_DAO GradeDAO = Grade_DAO.getInstance();
		GradeDAO.setConnection(con);
		int updateCount = GradeDAO.updateArticle(article);
		
		if(updateCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
		
	}

}
