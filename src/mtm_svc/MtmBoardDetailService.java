package mtm_svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import mtm_dao.MtmBoardDAO;
import mtm_vo.MtmBoardBean;

public class MtmBoardDetailService {

	public MtmBoardBean getArticle(int MTM_num) throws Exception{
		// TODO Auto-generated method stub
		
		MtmBoardBean article = null;
		Connection con = getConnection();
		MtmBoardDAO boardDAO = MtmBoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateReadCount(MTM_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = boardDAO.selectArticle(MTM_num);
		close(con);
		return article;
		
	}

}
