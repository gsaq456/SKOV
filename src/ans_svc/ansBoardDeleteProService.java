package ans_svc;

import static ans_db.JdbcUtil.close;
import static ans_db.JdbcUtil.commit;
import static ans_db.JdbcUtil.getConnection;
import static ans_db.JdbcUtil.rollback;

import java.sql.Connection;

import ans_dao.ansBoardDAO;

public class ansBoardDeleteProService {

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

	public boolean removeArticle(int ans_num) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		ansBoardDAO ansboardDAO = ansBoardDAO.getInstance();
		ansboardDAO.setConnection(con);
		int deleteCount = ansboardDAO.deleteArticle(ans_num);
		
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
