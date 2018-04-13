package report_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import report_dao.ReportBoardDAO;
import report_vo.ReportBoardBean;
public class ReportBoardWriteProService {

	public boolean registArticle(ReportBoardBean boardBean) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		ReportBoardDAO boardDAO = ReportBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int insertCount = boardDAO.insertArticle(boardBean);
		
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
