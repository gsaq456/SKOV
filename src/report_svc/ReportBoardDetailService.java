package report_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import report_dao.ReportBoardDAO;
import report_vo.ReportBoardBean;

public class ReportBoardDetailService {

	public ReportBoardBean getArticle(int REPORT_num) throws Exception{
		// TODO Auto-generated method stub
		
		ReportBoardBean article = null;
		Connection con = getConnection();
		ReportBoardDAO boardDAO = ReportBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateReadCount(REPORT_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = boardDAO.selectArticle(REPORT_num);
		close(con);
		return article;
		
	}

}
