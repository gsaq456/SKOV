package ans_svc;

import static ans_db.JdbcUtil.*;

import java.sql.Connection;

import ans_dao.ansBoardDAO;
import ans_vo.ansBoardBean;

public class ansBoardDetailService {

	public ansBoardBean getArticle(int ans_num) throws Exception{
		// TODO Auto-generated method stub
		
		ansBoardBean article = null;
		Connection con = getConnection();
		ansBoardDAO ansboardDAO = ansBoardDAO.getInstance();
		ansboardDAO.setConnection(con);
		int updateCount = ansboardDAO.updateReadCount(ans_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = ansboardDAO.selectArticle(ans_num);
		close(con);
		return article;
		
	}

}
