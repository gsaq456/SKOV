package ans_svc;

import static ans_db.JdbcUtil.*;

import java.sql.Connection;

import ans_dao.ansBoardDAO;
import ans_vo.ansBoardBean;
public class ansBoardWriteProService {

	public boolean registArticle(ansBoardBean ansboardBean) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(ansboardBean);
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		ansBoardDAO ansboardDAO = ansBoardDAO.getInstance();
		ansboardDAO.setConnection(con);
		int insertCount = ansboardDAO.insertArticle(ansboardBean);
		
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
