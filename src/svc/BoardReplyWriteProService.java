
package svc;

import java.sql.Connection;
import dao.BoardDAO;
import vo.ReplyBean;
import static db.jdbcUtil.*;

public class BoardReplyWriteProService {

	public boolean registReply(ReplyBean replyBean) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isReplyWriteSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int insertCount = boardDAO.insertReply(replyBean);
		
		if(insertCount > 0){
			commit(con);
			isReplyWriteSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isReplyWriteSuccess;
		
	}
}

