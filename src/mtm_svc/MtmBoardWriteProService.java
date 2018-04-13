package mtm_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import mtm_dao.MtmBoardDAO;
import mtm_vo.MtmBoardBean;
public class MtmBoardWriteProService {

	public boolean registArticle(MtmBoardBean boardBean) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
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
