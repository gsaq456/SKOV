package report_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import report_dao.ReportBoardDAO;
import report_vo.ReportBoardBean;

public class ReportBoardModifyProService {

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

	public boolean modifyArticle(ReportBoardBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		ReportBoardDAO boardDAO = ReportBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateArticle(article);
		
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
