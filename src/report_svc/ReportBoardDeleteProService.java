package report_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import report_dao.ReportBoardDAO;

public class ReportBoardDeleteProService {

	public boolean isArticleWriter(int REPORT_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		ReportBoardDAO boardDAO = ReportBoardDAO.getInstance();
		boardDAO.setConnection(con);
		isArticleWriter = boardDAO.isArticleBoardWriter(REPORT_num, pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean removeArticle(int REPORT_num) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		ReportBoardDAO boardDAO = ReportBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = boardDAO.deleteArticle(REPORT_num);
		
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
