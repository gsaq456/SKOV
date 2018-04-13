package Notice_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import Notice_dao.noticeBoardDAO;
import Notice_vo.noticeBoardBean;

public class noticeBoardDetailService {

	public noticeBoardBean getArticle(int notice_num) throws Exception{
		// TODO Auto-generated method stub
		
		noticeBoardBean article = null;
		Connection con = getConnection();
		noticeBoardDAO noticeboardDAO = noticeBoardDAO.getInstance();
		noticeboardDAO.setConnection(con);
		int updateCount = noticeboardDAO.updateReadCount(notice_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = noticeboardDAO.selectArticle(notice_num);
		close(con);
		return article;
		
	}

}
