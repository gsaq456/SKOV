package Notice_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import Notice_dao.noticeBoardDAO;
import Notice_vo.noticeBoardBean;
public class noticeBoardWriteProService {

	public boolean registArticle(noticeBoardBean noticeboardBean) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(noticeboardBean);
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		noticeBoardDAO noticeboardDAO = noticeBoardDAO.getInstance();
		noticeboardDAO.setConnection(con);
		int insertCount = noticeboardDAO.insertArticle(noticeboardBean);
		
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
