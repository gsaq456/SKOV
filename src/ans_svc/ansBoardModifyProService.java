package ans_svc;

import static ans_db.JdbcUtil.*;

import java.sql.Connection;

import ans_dao.ansBoardDAO;
import ans_vo.ansBoardBean;

public class ansBoardModifyProService {

	public boolean isArticleWriter(int ans_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		ansBoardDAO ansboardDAO = ansBoardDAO.getInstance();
		ansboardDAO.setConnection(con);
		isArticleWriter = ansboardDAO.isArticleBoardWriter(ans_num,pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(ansBoardBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		ansBoardDAO ansboardDAO = ansBoardDAO.getInstance();
		ansboardDAO.setConnection(con);
		int updateCount = ansboardDAO.updateArticle(article);
		
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
