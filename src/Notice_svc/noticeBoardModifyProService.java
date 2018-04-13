package Notice_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import Notice_dao.noticeBoardDAO;
import Notice_vo.noticeBoardBean;

public class noticeBoardModifyProService {

	public boolean isArticleWriter(int notice_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		noticeBoardDAO noticeboardDAO = noticeBoardDAO.getInstance();
		noticeboardDAO.setConnection(con);
		isArticleWriter = noticeboardDAO.isArticleBoardWriter(notice_num,pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(noticeBoardBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		noticeBoardDAO noticeboardDAO = noticeBoardDAO.getInstance();
		noticeboardDAO.setConnection(con);
		int updateCount = noticeboardDAO.updateArticle(article);
		
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
