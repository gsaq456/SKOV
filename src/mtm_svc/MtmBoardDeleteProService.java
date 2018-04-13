package mtm_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import mtm_dao.MtmBoardDAO;

public class MtmBoardDeleteProService {

	public boolean isArticleWriter(int MTM_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
		boardDAO.setConnection(con);
		isArticleWriter = boardDAO.isArticleBoardWriter(MTM_num, pass);
		close(con);
		return isArticleWriter;
		
	}

	public boolean removeArticle(int MTM_num) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = boardDAO.deleteArticle(MTM_num);
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}

}
