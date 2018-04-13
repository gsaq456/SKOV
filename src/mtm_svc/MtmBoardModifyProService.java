package mtm_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import mtm_dao.MtmBoardDAO;
import mtm_vo.MtmBoardBean;

public class MtmBoardModifyProService {

	public boolean isArticleWriter(int mtm_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
		boardDAO.setConnection(con);
		isArticleWriter = boardDAO.isArticleBoardWriter(mtm_num, pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(MtmBoardBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
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
