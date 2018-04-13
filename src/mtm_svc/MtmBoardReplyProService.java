package mtm_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import mtm_dao.MtmBoardDAO;
import mtm_vo.MtmBoardBean;

public class MtmBoardReplyProService {

	public boolean replyArticle(MtmBoardBean article) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
		boardDAO.setConnection(con);
		insertCount = boardDAO.insertReplyArticle(article);
		
		if(insertCount > 0){
			commit(con);
			isReplySuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isReplySuccess;
		
	}

}
